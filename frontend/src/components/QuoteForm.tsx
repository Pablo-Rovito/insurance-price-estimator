import React, { useState } from "react";
import { CoverageEnum } from "../enums/CoverageEnum.ts";
import { QuoteResponse } from "../interfaces/QuoteResponse.ts";
import { QuoteData } from "../interfaces/QuoteData.ts";
import Table from "./Table.tsx";

const QuoteForm: React.FC = () => {
    const headers: string[] = ["Parameter", "Value"];
    const rows: string[] = ["Policy Holder", "Premium", "Coverage Type", "Discount Percentage", "End Price"];
    const historyBackground: string = "#f0f0f0";
    const historyWidth: string = "100%";

    const [name, setName] = useState<string>("");
    const [age, setAge] = useState<number>("");
    const [coverage, setCoverage] = useState<CoverageEnum>(CoverageEnum.SUPER_COVERAGE);
    const [response, setResponse] = useState<QuoteResponse | null>(null);
    const [history, setHistory] = useState<QuoteResponse[]>([]);

    const resetForm = () => {
        setAge("");
        setName("");
        setCoverage(CoverageEnum.SUPER_COVERAGE);
    };

    const listResponseData = (quote: QuoteResponse) => {
        return [
            quote.policyHolder,
            quote.premium.toString(),
            quote.coverageType,
            quote.discount.discountPercentage.toString(),
            quote.discount.endPrice.toString()
        ]
    }

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        const quoteData: QuoteData = {
            age,
            name,
            coverage
        };

        try {
            const response = await fetch("http://localhost:8080/quotes", {
                method: "POST",
                headers: {
                "Content-Type": "application/json",
                },
                body: JSON.stringify(quoteData),
            });
            
            if (!response.ok) {
                console.log(response)
                throw new Error("Error getting the quote!");
            }

            const data: QuoteResponse = await response.json();
            setResponse(data);

            setHistory((prevHistory) => [...prevHistory, data]);
            resetForm();
            alert("Success getting the quote!");
        } catch (error) {
            console.error(error);
            alert("There was a problem getting the quote");
        }   
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <input type="number" value={age} onChange={(e) => setAge(Number(e.target.value))} placeholder="age" required/>            
                <input type="text" value={name} onChange={(e) => setName(e.target.value)} placeholder="name" required/>
                <select value={coverage} onChange={(e) => setCoverage(e.target.value as CoverageEnum)} required>
                    {Object.values(CoverageEnum).map((coverageValue) => (
                        <option key={coverageValue} value={coverageValue}>
                            {coverageValue}
                        </option>
                    ))}
                </select>
                <button type="submit">Get Quote</button>
            </form>

            {response && (
                <div>
                    <h3>Quote</h3>
                    <Table headers={headers} rows={rows} data={listResponseData(response)}/>
                </div>
            )}

            {history.length > 0 && (
                <div style={{backgroundColor: historyBackground, width: historyWidth}}>
                    <h3>History</h3>
                    {history.map((quote: QuoteResponse) => <Table key={quote.id} headers={headers} rows={rows} data={listResponseData(quote)}/>)}
                </div>
            )}
        </div>
    );
};

export default QuoteForm;