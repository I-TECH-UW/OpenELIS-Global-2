package org.openelisglobal.fhir.providers;

import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.Delete;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.OptionalParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.annotation.Update;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.param.ReferenceAndListParam;
import ca.uhn.fhir.rest.param.StringAndListParam;
import ca.uhn.fhir.rest.param.TokenAndListParam;
import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import ca.uhn.fhir.rest.server.exceptions.InvalidRequestException;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Location;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.common.valueholder.BaseObject;
import org.openelisglobal.dataexchange.fhir.FhirUtil;
import org.openelisglobal.storage.fhir.StorageLocationFhirTransform;
import org.openelisglobal.storage.service.StorageBoxService;
import org.openelisglobal.storage.service.StorageDeviceService;
import org.openelisglobal.storage.service.StorageLocationService;
import org.openelisglobal.storage.service.StorageRackService;
import org.openelisglobal.storage.service.StorageRoomService;
import org.openelisglobal.storage.service.StorageShelfService;
import org.openelisglobal.storage.valueholder.StorageBox;
import org.openelisglobal.storage.valueholder.StorageDevice;
import org.openelisglobal.storage.valueholder.StorageRack;
import org.openelisglobal.storage.valueholder.StorageRoom;
import org.openelisglobal.storage.valueholder.StorageShelf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FHIR R4 provider exposing the sample-storage hierarchy (room, equipment,
 * shelf, rack, box) as mCSD Location resources.
 *
 * <p>
 * Every level shares one id space, so a Location id is resolved by trying each
 * level in turn. The storage level of an inbound resource is read from the
 * {@code storage-hierarchy} tag, falling back to {@code physicalType.text}.
 * Read and write operations use the OpenELIS database; the result is mirrored
 * to the FHIR store on a best-effort basis. Search still forwards to the FHIR
 * store.
 *
 * <p>
 * Supported operations:
 * <ul>
 * <li>READ: GET /fhir/Location/{uuid}</li>
 * <li>CREATE: POST /fhir/Location (a client-supplied id is ignored)</li>
 * <li>UPDATE: PUT /fhir/Location/{uuid} (the URL id is authoritative)</li>
 * <li>DELETE: DELETE /fhir/Location/{uuid} (deactivates the storage
 * entity)</li>
 * <li>SEARCH: GET /fhir/Location?identifier=...</li>
 * </ul>
 */
@Component
public class LocationProvider implements IResourceProvider {

    private static final String ROOM = "Storage Room";
    private static final String EQUIPMENT = "Storage Equipment";
    private static final String SHELF = "Storage Shelf";
    private static final String RACK = "Storage Rack";
    private static final String BOX = "Storage Box";

    @Autowired
    private StorageLocationFhirTransform transform;

    @Autowired
    private StorageRoomService roomService;

    @Autowired
    private StorageDeviceService deviceService;

    @Autowired
    private StorageShelfService shelfService;

    @Autowired
    private StorageRackService rackService;

    @Autowired
    private StorageBoxService boxService;

    @Autowired
    private StorageLocationService locationService;

    @Autowired
    private FhirUtil util;

    private final List<StorageLevel<?>> levels = new ArrayList<>();

    /**
     * One storage level: how to look it up, render it, mirror it and deactivate it.
     * The five entity types share no interface, so the per-type calls live in these
     * lambdas rather than in five copies of each operation.
     */
    private record StorageLevel<T extends BaseObject<Integer>>(String category, BaseObjectService<T, Integer> service,
            Function<T, Location> toLocation, BiConsumer<T, String> deactivate) {

        Location read(UUID uuid, StorageLocationFhirTransform transform) {
            T entity = transform.getItemByFhirId(uuid, service);
            return entity == null ? null : toLocation.apply(entity);
        }

        Location delete(UUID uuid, String sysUserId, StorageLocationFhirTransform transform) {
            T entity = transform.getItemByFhirId(uuid, service);
            if (entity == null) {
                return null;
            }
            deactivate.accept(entity, sysUserId);
            T updated = service.update(entity);
            if (updated == null) {
                throw new InternalErrorException("Failed to deactivate " + category);
            }
            Location location = toLocation.apply(updated);
            transform.syncToFhir(location, false);
            return location;
        }
    }

    @PostConstruct
    void registerLevels() {
        levels.add(new StorageLevel<>(ROOM, roomService, transform::transformToFhirLocation, (room, user) -> {
            room.setActive(false);
            room.setSysUserId(user);
        }));
        levels.add(new StorageLevel<>(EQUIPMENT, deviceService, transform::transformToFhirLocation, (device, user) -> {
            device.setActive(false);
            device.setSysUserId(user);
        }));
        levels.add(new StorageLevel<>(SHELF, shelfService, transform::transformToFhirLocation, (shelf, user) -> {
            shelf.setActive(false);
            shelf.setSysUserId(user);
        }));
        levels.add(new StorageLevel<>(RACK, rackService, transform::transformToFhirLocation, (rack, user) -> {
            rack.setActive(false);
            rack.setSysUserId(user);
        }));
        levels.add(new StorageLevel<>(BOX, boxService, transform::transformToFhirLocation, (box, user) -> {
            box.setActive(false);
            box.setSysUserId(user);
        }));
    }

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return Location.class;
    }

    @Read
    public Location readLocation(@IdParam IdType theId) {
        String method = "readLocation";
        try {
            UUID uuid = requireUuid(theId, method);
            for (StorageLevel<?> level : levels) {
                Location location = level.read(uuid, transform);
                if (location != null) {
                    return location;
                }
            }
            throw new ResourceNotFoundException("Location/" + theId.getIdPart());
        } catch (ResourceNotFoundException | InvalidRequestException e) {
            throw e;
        } catch (Exception e) {
            LogEvent.logError(this.getClass().getSimpleName(), method,
                    "Unexpected error while reading Location: " + FhirProviderUtils.safeMessage(e));
            throw new InternalErrorException("Unexpected server error while reading Location", e);
        }
    }

    @Create
    public MethodOutcome createLocation(@ResourceParam Location location, HttpServletRequest request) {
        String method = "createLocation";

        if (location == null) {
            throw new InvalidRequestException("Location resource cannot be null");
        }

        try {
            // FHIR create: the server assigns the id, so a client-supplied one must not
            // turn this into an update of an existing storage entity.
            location.setId((String) null);
            Location created = persist(location, FhirProviderUtils.getSysUserId(request), true);
            return FhirProviderUtils.buildCreateOutcome(created);
        } catch (InvalidRequestException | UnprocessableEntityException | ResourceNotFoundException e) {
            LogEvent.logError(this.getClass().getSimpleName(), method, FhirProviderUtils.safeMessage(e));
            throw e;
        } catch (Exception e) {
            LogEvent.logError(this.getClass().getSimpleName(), method, FhirProviderUtils.safeMessage(e));
            throw new InternalErrorException(
                    "Unexpected server error while creating Location: " + FhirProviderUtils.safeMessage(e), e);
        }
    }

    @Update
    public MethodOutcome updateLocation(@IdParam IdType theId, @ResourceParam Location location,
            HttpServletRequest request) {
        String method = "updateLocation";

        if (location == null) {
            throw new InvalidRequestException("Location resource cannot be null");
        }

        try {
            requireUuid(theId, method);
            if (location.hasId() && !theId.getIdPart().equals(location.getIdElement().getIdPart())) {
                throw new InvalidRequestException("Location.id " + location.getIdElement().getIdPart()
                        + " does not match the id in the request URL " + theId.getIdPart());
            }
            location.setId(theId.getIdPart());
            Location updated = persist(location, FhirProviderUtils.getSysUserId(request), false);
            return FhirProviderUtils.buildUpdateOutcome(updated);
        } catch (InvalidRequestException | UnprocessableEntityException | ResourceNotFoundException e) {
            LogEvent.logError(this.getClass().getSimpleName(), method, FhirProviderUtils.safeMessage(e));
            throw e;
        } catch (Exception e) {
            LogEvent.logError(this.getClass().getSimpleName(), method, FhirProviderUtils.safeMessage(e));
            throw new InternalErrorException(
                    "Unexpected server error while updating Location: " + FhirProviderUtils.safeMessage(e), e);
        }
    }

    /**
     * Deactivates the storage entity rather than deleting it: assignments and
     * history stay intact and a subsequent read reports status inactive.
     */
    @Delete
    public MethodOutcome deleteLocation(@IdParam IdType theId, HttpServletRequest request) {
        String method = "deleteLocation";

        try {
            UUID uuid = requireUuid(theId, method);
            String sysUserId = FhirProviderUtils.getSysUserId(request);
            for (StorageLevel<?> level : levels) {
                if (level.delete(uuid, sysUserId, transform) != null) {
                    return FhirProviderUtils.buildDeleteOutcome(theId, "Location");
                }
            }
            throw new ResourceNotFoundException("Location/" + theId.getIdPart());
        } catch (ResourceNotFoundException | InvalidRequestException e) {
            LogEvent.logError(this.getClass().getSimpleName(), method,
                    "Client error: " + FhirProviderUtils.safeMessage(e));
            throw e;
        } catch (InternalErrorException e) {
            LogEvent.logError(this.getClass().getSimpleName(), method,
                    "Internal error: " + FhirProviderUtils.safeMessage(e));
            throw e;
        } catch (Exception e) {
            LogEvent.logError(this.getClass().getSimpleName(), method,
                    "Unhandled exception: " + FhirProviderUtils.safeMessage(e));
            throw new InternalErrorException(
                    "Unexpected server error while deleting Location: " + FhirProviderUtils.safeMessage(e), e);
        }
    }

    @Search
    public Bundle searchLocationBundle(@OptionalParam(name = Location.SP_IDENTIFIER) TokenAndListParam identifier,
            @OptionalParam(name = Location.SP_NAME) StringAndListParam name,
            @OptionalParam(name = Location.SP_STATUS) TokenParam status,
            @OptionalParam(name = Location.SP_PARTOF) ReferenceAndListParam partOf,
            @OptionalParam(name = Location.SP_ORGANIZATION) ReferenceAndListParam organization,
            @OptionalParam(name = "physical-type") TokenAndListParam physicalType,
            @OptionalParam(name = Location.SP_TYPE) TokenAndListParam type,
            @OptionalParam(name = "_tag") TokenAndListParam tag, HttpServletRequest request) {

        final String methodName = "searchLocationBundle";

        try {
            Bundle bundle = util.forwardSearchToFhirStore(request);

            if (bundle == null) {
                bundle = new Bundle();
            }
            if (bundle.getType() == null) {
                bundle.setType(Bundle.BundleType.SEARCHSET);
            }
            if (bundle.getEntry() == null) {
                bundle.setEntry(new ArrayList<>());
            }

            return bundle;

        } catch (Exception e) {
            LogEvent.logError(this.getClass().getSimpleName(), methodName,
                    "Error searching Locations: " + FhirProviderUtils.safeMessage(e));
            throw new InternalErrorException("Unexpected server error while searching Locations", e);
        }
    }

    private Location persist(Location location, String sysUserId, boolean isCreate) {
        if (sysUserId == null || sysUserId.isBlank()) {
            throw new InvalidRequestException("System user ID is required");
        }

        String category = storageCategory(location);
        try {
            switch (category) {
            case ROOM:
                return persistRoom(location, sysUserId, isCreate);
            case EQUIPMENT:
                return persist(transform.createOrUpdateStorageDeviceFromLocation(location), StorageDevice.class,
                        transform::transformToFhirLocation, location, sysUserId, isCreate);
            case SHELF:
                return persist(transform.createOrUpdateStorageShelfFromLocation(location), StorageShelf.class,
                        transform::transformToFhirLocation, location, sysUserId, isCreate);
            case RACK:
                return persist(transform.createOrUpdateStorageRackFromLocation(location), StorageRack.class,
                        transform::transformToFhirLocation, location, sysUserId, isCreate);
            case BOX:
                return persist(transform.createOrUpdateStorageBoxFromLocation(location), StorageBox.class,
                        transform::transformToFhirLocation, location, sysUserId, isCreate);
            default:
                throw new InvalidRequestException("Unsupported storage level: " + category);
            }
        } catch (LIMSRuntimeException | IllegalArgumentException e) {
            throw new UnprocessableEntityException(FhirProviderUtils.safeMessage(e));
        }
    }

    private Location persistRoom(Location location, String sysUserId, boolean isCreate) {
        StorageRoom room = transform.createStorageRoomFromLocation(location);
        room.setSysUserId(sysUserId);

        StorageRoom saved;
        if (isCreate) {
            saved = locationService.createRoom(room);
        } else {
            requireExisting(room, location);
            saved = locationService.updateRoom(room.getId(), room);
        }
        if (saved == null) {
            throw new InternalErrorException("Failed to save " + ROOM);
        }

        Location result = transform.transformToFhirLocation(saved);
        transform.syncToFhir(result, isCreate);
        return result;
    }

    private <T extends BaseObject<Integer>> Location persist(T entity, Class<T> type, Function<T, Location> toLocation,
            Location location, String sysUserId, boolean isCreate) {
        entity.setSysUserId(sysUserId);

        Integer id;
        if (isCreate) {
            id = locationService.insert(entity);
        } else {
            requireExisting(entity, location);
            locationService.update(entity);
            id = entity.getId();
        }

        T saved = type.cast(locationService.get(id, type));
        if (saved == null) {
            throw new InternalErrorException("Failed to save " + type.getSimpleName());
        }

        Location result = toLocation.apply(saved);
        transform.syncToFhir(result, isCreate);
        return result;
    }

    private void requireExisting(BaseObject<Integer> entity, Location location) {
        if (entity.getId() == null) {
            throw new ResourceNotFoundException("Location/" + location.getIdElement().getIdPart());
        }
    }

    /**
     * The storage level travels in the {@code storage-hierarchy} tag that every
     * outbound Location carries; {@code physicalType.text} is accepted as a
     * fallback for hand-written resources.
     */
    private String storageCategory(Location location) {
        for (Coding tag : location.getMeta().getTag()) {
            if (StorageLocationFhirTransform.STORAGE_HIERARCHY_TAG_SYSTEM.equals(tag.getSystem()) && tag.hasCode()) {
                switch (tag.getCode()) {
                case "room":
                    return ROOM;
                case "device":
                    return EQUIPMENT;
                case "shelf":
                    return SHELF;
                case "rack":
                    return RACK;
                case "box":
                    return BOX;
                default:
                    break;
                }
            }
        }

        if (location.hasPhysicalType() && location.getPhysicalType().hasText()
                && !location.getPhysicalType().getText().isBlank()) {
            return location.getPhysicalType().getText().trim();
        }

        throw new InvalidRequestException("Location must name its storage level with a "
                + StorageLocationFhirTransform.STORAGE_HIERARCHY_TAG_SYSTEM
                + " tag (room, device, shelf, rack, box) or physicalType.text (" + ROOM + ", " + EQUIPMENT + ", "
                + SHELF + ", " + RACK + ", " + BOX + ")");
    }

    private UUID requireUuid(IdType theId, String method) {
        FhirProviderUtils.validateIdParam(theId, "Location", getClass().getSimpleName(), method);
        try {
            return UUID.fromString(theId.getIdPart());
        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("Location ID must be a valid UUID");
        }
    }

}
