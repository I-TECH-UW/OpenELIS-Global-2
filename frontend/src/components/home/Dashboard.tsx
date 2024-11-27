import React from "react";
import { Tile, ClickableTile, Loading } from "@carbon/react";
import "./Dashboard.css";
import { ArrowUpRight } from "@carbon/react/icons";
import { useState, useEffect, useRef } from "react";
import { getFromOpenElisServer } from "../utils/Utils.js";
import { FormattedMessage } from "react-intl";
interface DashBoardProps {}

interface Tile {
  title: string | JSX.Element;
  subTitle: string | JSX.Element;
  value: number;
}

const HomeDashBoard: React.FC<DashBoardProps> = () => {
  const [counts, setCounts] = useState({
    ordersInProgress: 0,
    ordersReadyForValidation: 0,
    ordersCompletedToday: 0,
    patiallyCompletedToday: 0,
    orderEnterdByUserToday: 0,
    ordersRejectedToday: 0,
    unPritendResults: 0,
    incomigOrders: 0,
    averageTurnAroudTime: 0,
    delayedTurnAround: 0,
  });
  const [loading, setLoading] = useState(true);
  const componentMounted = useRef(true);

  useEffect(() => {
    getFromOpenElisServer("/rest/home-dashboard/metrics", loadCount);

    return () => {
      // This code runs when component is unmounted
      componentMounted.current = false;
    };
  }, []);

  const loadCount = (data) => {
    if (componentMounted.current) {
      setCounts(data);
      setLoading(false);
    }
  };

  const tileList: Array<Tile> = [
    {
      title: <FormattedMessage id="dashboard.in.progress.label" />,
      subTitle: <FormattedMessage id="dashboard.in.progress.subtitle.label" />,
      value: counts.ordersInProgress,
    },
    {
      title: <FormattedMessage id="dashboard.validation.ready.label" />,
      subTitle: (
        <FormattedMessage id="dashboard.validation.ready.subtitle.label" />
      ),
      value: counts.ordersReadyForValidation,
    },
    {
      title: <FormattedMessage id="dashboard.complete.orders.label" />,
      subTitle: <FormattedMessage id="dashboard.orders.subtitle.label" />,
      value: counts.ordersCompletedToday,
    },
    {
      title: <FormattedMessage id="dashboard.partially.completed.label" />,
      subTitle: (
        <FormattedMessage id="dashboard.partially.completed..subtitle.label" />
      ),
      value: counts.patiallyCompletedToday,
    },
    {
      title: <FormattedMessage id="dashboard.user.orders.label" />,
      subTitle: <FormattedMessage id="dashboard.user.orders.subtitle.label" />,
      value: counts.orderEnterdByUserToday,
    },
    {
      title: <FormattedMessage id="dashboard.rejected.orders" />,
      subTitle: <FormattedMessage id="dashboard.rejected.orders.subtitle" />,
      value: counts.ordersRejectedToday,
    },
    {
      title: <FormattedMessage id="dashboard.unprints.results.label" />,
      subTitle: (
        <FormattedMessage id="dashboard.unprints.results.subtitle.label" />
      ),
      value: counts.unPritendResults,
    },
    {
      title: <FormattedMessage id="sidenav.label.incomingorder" />,
      subTitle: <FormattedMessage id="label.electronic.orders" />,
      value: counts.incomigOrders,
    },
    {
      title: <FormattedMessage id="dashboard.avg.turn.around.label" />,
      subTitle:  <FormattedMessage id="dashboard.avg.turn.around.subtitle.label"/>,
      value: counts.averageTurnAroudTime,
    },
    {
      title: <FormattedMessage id="dashboard.turn.around.label" />,
      subTitle: <FormattedMessage id="dashboard.turn.around.subtitle.label" />,
      value: counts.delayedTurnAround,
    },
  ];
  return (
    <>
      {loading && <Loading description="Loading Dasboard..." />}
      <div className="dashboard-container">
        {tileList.map((tile, index) => (
          <ClickableTile key={index} className="dashboard-tile">
            <h3 className="tile-title">{tile.title}</h3>
            <p className="tile-subtitle">{tile.subTitle}</p>
            <p className="tile-value">{tile.value}</p>
            <div className="tile-icon">
              <ArrowUpRight size={20} className="clickable-icon" />
            </div>
          </ClickableTile>
        ))}
      </div>
    </>
  );
};
export default HomeDashBoard;
