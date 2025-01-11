import React, { useState } from "react";
import { CoverageEnum } from "../enums/CoverageEnum.ts";
import { QuoteResponse } from "../interfaces/QuoteResponse.ts";
import { QuoteData } from "../interfaces/QuoteData.ts";

const QuoteForm: React.FC = () => {
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
            setHistory([...history, data]);
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
                    <pre>{JSON.stringify(response, null, 2)}</pre>
                </div>
            )}

            {history.length && (
                <div>
                    <h3>History</h3>
                    <ul>
                        {history.map((quote: QuoteResponse) => (
                            <li key={quote.id}>
                                <pre>{JSON.stringify(response, null, 2)}</pre>
                            </li>
                        ))}
                    </ul>
                </div>    
            )}
        </div>
    );
};

export default QuoteForm;