import React, {useEffect, useState} from 'react'
import SearchForm from "./SearchForm";
import Validation from "./Validation";

const Index = () => {
    const [results, setResults] = useState();
    return (
        <>
            <SearchForm setResults={setResults}/>
            <Validation results={results}/>
        </>
    )
}

export default Index;
