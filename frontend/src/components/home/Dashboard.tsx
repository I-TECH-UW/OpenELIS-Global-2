import React from "react";
import { Icon, Link, Grid, Tile, ClickableTile, Column, ExpandableTile, TileAboveTheFoldContent, TileBelowTheFoldContent } from '@carbon/react';
import './Dashboard.css';
import { ArrowUpRight } from '@carbon/react/icons';
interface DashBoardProps {
}

const HomeDashBoard: React.FC<DashBoardProps> = () => {

    const tilesData = [
        { title: 'In Progress', subtitle: 'Awaiting Result Entry', value: '0' },
        { title: 'Ready For Validation', subtitle: 'Awaiting Review', value: '0' },
        { title: 'Orders Completed Today', subtitle: 'Total Orders Completed Today', value: '0' },
        { title: 'Patiallly Completed Today', subtitle: 'Total Orders Completed Today', value: '0' },
        { title: 'Orders Entered By User', subtitle: 'Entered by user Today', value: '0' },
        { title: 'Orders Rejected', subtitle: 'Rejected By Lab Today', value: '0' },
        { title: 'Un Printed Results', subtitle: 'Un Prited Results Today', value: '0' },
        { title: 'Incoming Orders', subtitle: 'Electronic Orders', value: '0' },
        { title: 'Average Turn Around time', subtitle: 'Reception to Validation', value: '0' },
        { title: 'Delayed Turn Around', subtitle: 'More Than 96 hours', value: '0' },
        // { title: 'Tile 11', subtitle: 'Subtitle 1', value: '0' },
        // { title: 'Tile 12', subtitle: 'Subtitle 1', value: '100' },
        // ... add data for other tiles
    ];

    return (
        <>
            <div className="dashboard-container">
                {tilesData.map((tile, index) => (
                    <ClickableTile  key={index} className="dashboard-tile">
                        <h3 className="tile-title">{tile.title}</h3>
                        <p className="tile-subtitle">{tile.subtitle}</p>
                        <p className="tile-value">{tile.value}</p>
                        <div className="tile-icon">
                            <Link href="#">
                                <ArrowUpRight
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