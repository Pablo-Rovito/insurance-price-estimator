import React from "react";

const Table: React.FC = ({headers, rows, data}) => {
    return (
        <table border="1" style={{ marginBottom: "20px", width: "100%" }}>
            <thead>
                <tr>
                    {headers.map((header) => (
                        <th key={header}>{header}</th>
                    ))}
                </tr>
            </thead>
            <tbody>
                {rows.map((row, index) => (
                    <tr key={row}>
                        <td>{row}</td>
                        <td>{data[index]}</td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
}

export default Table;