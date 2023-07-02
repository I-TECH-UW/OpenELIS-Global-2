import React, {Component} from 'react';
import {Button, Row} from "@carbon/react";

class OrderSuccessMessage extends Component {
    render() {
        return (
            <div className="orderLegendBody">
                <div className="orderEntrySuccessMsg">
                    <img
                        src={`images/success-icon.png`}
                        alt="Order Entry saved successfully"
                        width="120"
                        height="120"
                    />
                    <h4>Save successful</h4>
                    <Row>
                        <Button className="printBarCodeBtn">Print Barcode</Button>
                    </Row>
                    <Row>
                        <Button className="placeAnotherOrderBtn" kind="tertiary">Place same site order</Button>
                    </Row>
                </div>
            </div>
        );
    }
}

export default OrderSuccessMessage;
