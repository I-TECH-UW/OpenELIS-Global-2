import React, {useEffect, useState} from 'react';
import {Button, Row, TextInput} from "@carbon/react";
import config from "../../config.json";
import {SampleOrderFormValues} from "../formModel/innitialValues/OrderEntryFormValues";
import {sampleObject} from "./Index";


const OrderSuccessMessage = (props) => {
    const {orderFormValues, setOrderFormValues, setSamples, setPage} = props;
    const [allowPrintBarCodes, setAllowPrintBarCodes] = useState(false);
    const [numberOfPrints, setNumberOfPrints] = useState(1);


    const handlePrintBarCode = () => {
        setAllowPrintBarCodes(true)
    }
    const handleNumberOfPrints = (e) => {
        setNumberOfPrints(e.target.value);
    }
    const confirmPrintHandler = () => {
        let barcodesPdf = config.serverBaseUrl + `/LabelMakerServlet?labNo=${orderFormValues.sampleOrderItems.labNo}&type=order&quantity=${numberOfPrints}`;
        window.open(barcodesPdf);
    }

    const handleAnotherSiteOrder = () => {
        const siteId = orderFormValues.sampleOrderItems.referringSiteId;
        const siteName = orderFormValues.sampleOrderItems.referringSiteName;
        const providerId = orderFormValues.sampleOrderItems.providerId;
        const providerFirstName = orderFormValues.sampleOrderItems.providerFirstName;
        const providerLastName = orderFormValues.sampleOrderItems.providerLastName;
        const providerWorkPhone = orderFormValues.sampleOrderItems.providerWorkPhone;
        const providerFax = orderFormValues.sampleOrderItems.providerFax;
        const providerEmail = orderFormValues.sampleOrderItems.providerEmail;

        setOrderFormValues(SampleOrderFormValues);

        setOrderFormValues({
            ...SampleOrderFormValues,
            rememberSiteAndRequester: true,
            sampleOrderItems: {
                ...SampleOrderFormValues.sampleOrderItems,
                referringSiteId: siteId,
                referringSiteName: siteName,
                providerId: providerId,
                providerFirstName: providerFirstName,
                providerLastName: providerLastName,
                providerWorkPhone: providerWorkPhone,
                providerFax: providerFax,
                providerEmail: providerEmail
            }
        });
        setPage(0);
    }

    useEffect(() => {
        if (!orderFormValues.rememberSiteAndRequester) {
            setOrderFormValues(SampleOrderFormValues);
        }
        setSamples([sampleObject]);
    }, []);

    return (<div className="orderLegendBody">
        <div className="orderEntrySuccessMsg">
            <img
                src={`images/success-icon.png`}
                alt="Order Entry saved successfully"
                width="120"
                height="120"
            />
            <h4>Save successful</h4>
            <Row>
                <Button className="" onClick={handlePrintBarCode}>Print Barcode</Button>
            </Row>
            {allowPrintBarCodes &&
                <Row>
                    <div className="inlineDiv">
                        <TextInput id="quatityOfBarCodes" value={numberOfPrints} onChange={(e) => {
                            handleNumberOfPrints(e)
                        }} labelText="Number to Print"/>
                        <Button kind="danger" className="confirmPrintCodeBtn" onClick={confirmPrintHandler}
                                size='sm'>Confirm</Button>
                    </div>
                </Row>
            }

            <Row>
                {orderFormValues.rememberSiteAndRequester &&
                    <Button className="placeAnotherOrderBtn" kind="tertiary" onClick={handleAnotherSiteOrder}>Place same
                        site order</Button>}
            </Row>
        </div>
    </div>);
}

export default OrderSuccessMessage;
