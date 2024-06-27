import React, { useEffect } from "react"

import {getFromOpenElisServer} from "../utils/Utils"

interface props {
    title : string,
    api: string,
    query: string,
    limit?: number,
}


const [search, setSearch] = React.useState("");
const [data, setData] = React.useState([]);


useEffect(() => {
    const fetchData = async () => {

        getFromOpenElisServer("")

    }
}, [search])



export const AutoCompleteV2 = (props:props) => {

    
    return (
        <div>
            
        </div>
    )
}