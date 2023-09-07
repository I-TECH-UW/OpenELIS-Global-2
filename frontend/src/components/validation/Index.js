import React, { useContext, useState, useEffect } from "react";
import SearchForm from "./SearchForm";
import Validation from "./Validation";
import { AlertDialog } from "../common/CustomNotification";
import { NotificationContext } from "../layout/Layout";

const Index = () => {
  const { notificationVisible } = useContext(NotificationContext);
  const [results, setResults] = useState({ resultList: [] });
  return (
    <div className="orderLegendBody">
      {notificationVisible === true ? <AlertDialog /> : ""}
      <SearchForm setResults={setResults} />
      <Validation results={results} />
    </div>
  );
};

export default Index;
