import React from "react";
import { Icon, Link, Grid, Tile, ClickableTile, Column, ExpandableTile, TileAboveTheFoldContent, TileBelowTheFoldContent, Loading, InlineLoading } from '@carbon/react';
import './Dashboard.css';
import { ArrowUpRight } from '@carbon/react/icons';
import { useContext, useState, useEffect, useRef } from "react";
import { getFromOpenElisServer, postToOpenElisServer, getFromOpeElisServerSync } from '../utils/Utils.js';
interface DashBoardProps {
}

interface Tile {
    title: string,
    subTitle: string,
    value: number
}

const HomeDashBoard: React.FC<DashBoardProps> = () => {
    const [counts, setCounts] = useState({  ordersInProgress: 0, ordersReadyForValidation: 0, ordersCompletedToday: 0, patiallyCompletedToday: 0, orderEnterdByUserToday: 0, ordersRejectedToday: 0, unPritendResults: 0, incomigOrders: 0, averageTurnAroudTime: 0, delayedTurnAround: 0 });
    const [loading, setLoading] = useState(true);
    const componentMounted = useRef(true);

    useEffect(() => {
        getFromOpenElisServer("/rest/home-dashboard/counts", loadCount);

        return () => { // This code runs when component is unmounted
            componentMounted.current = false;
        }

    }, []);

    const loadCount = (data) => {
        if (componentMounted.current) {
            setCounts(data);
            setLoading(false);
        }
    }

    const tileList: Array<Tile> = [
        { title: 'In Progress', subTitle: 'Awaiting Result Entry', value: counts.ordersInProgress },
        { title: 'Ready For Validation', subTitle: 'Awaiting Review', value: counts.ordersReadyForValidation },
        { title: 'Orders Completed Today', subTitle: 'Total Orders Completed Today', value: counts.ordersCompletedToday },
        { title: 'Patiallly Completed Today', subTitle: 'Total Orders Completed Today', value: counts.patiallyCompletedToday },
        { title: 'Orders Entered By User', subTitle: 'Entered by user Today', value: counts.orderEnterdByUserToday },
        { title: 'Orders Rejected', subTitle: 'Rejected By Lab Today', value: counts.ordersRejectedToday },
        { title: 'Un Printed Results', subTitle: 'Un Prited Results Today', value: counts.unPritendResults },
        { title: 'Incoming Orders', subTitle: 'Electronic Orders', value: counts.incomigOrders },
        { title: 'Average Turn Around time', subTitle: 'Reception to Validation', value: counts.averageTurnAroudTime },
        { title: 'Delayed Turn Around', subTitle: 'More Than 96 hours', value: counts.delayedTurnAround },
    ];
    return (
        <>
            {loading && (
                <Loading description="Loading Dasboard..." />
            )}
            <div className="dashboard-container">
                {tileList.map((tile, index) => (
                    <ClickableTile key={index} className="dashboard-tile">
                        <h3 className="tile-title">{tile.title}</h3>
                        <p className="tile-subtitle">{tile.subTitle}</p>
                        <p className="tile-value">{tile.value}</p>
                        <div className="tile-icon">
                            <Link href="#">
                                <ArrowUpRight size={20}
                                    className="clickable-icon"
                                />
                            </Link>
                        </div>
                    </ClickableTile>
                ))}
            </div>



        </>
    );

}
export default HomeDashBoard;