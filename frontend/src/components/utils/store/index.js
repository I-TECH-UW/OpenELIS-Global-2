import { create } from 'zustand'

const availableStores = {}

export function createGlobalStore(name, initialState) {
    const existingStore = availableStores[name];

    if (existingStore && existingStore.active) {
        console.error('Cannot override an existing store. Ensure stores are created only once.');
        return existingStore.value;
    }

    const store = create()(() => initialState);

    availableStores[name] = {
        value: store,
        active: true,
    };

    return store;
}
