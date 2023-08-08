import React from "react";
import { Icon, Link, Grid, Tile, ClickableTile, Column, ExpandableTile, TileAboveTheFoldContent, TileBelowTheFoldContent } from '@carbon/react';
import './Dashboard.css';
import { ArrowUpRight } from '@carbon/react/icons';
import { useContext, useState, useEffect, useRef } from "react";
import { getFromOpenElisServer, postToOpenElisServer, getFromOpeElisServerSync } from '../utils/Utils.js';
interface DashBoardProps {
}

interface Tile {
    title: string,
    subTitle: string,
    value: string
}

const HomeDashBoard: React.FC<DashBoardProps> = () => {

    const [tileList, setTileList] = useState([Tile]);
    const componentMounted = useRef(true);

    useEffect(() => {
        getFromOpenElisServer("/rest/dashboard-tiles", loadTiles);

        return () => { // This code runs when component is unmounted
            componentMounted.current = false;
        }

    }, []);

    const loadTiles = (tiles) => {
        if (componentMounted.current) {
            setTileList(tiles);
        }
    }
    return (
        <>
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