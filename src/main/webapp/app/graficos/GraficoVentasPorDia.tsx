import React, { PureComponent } from 'react';
import {
  LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend,
} from 'recharts';

// TODO parsear la data directamente desde la API
const data = [
               { fecha: "2020-08-17", cantidadVentas: 2 },
               { fecha: "2020-08-16", cantidadVentas: 8 }
            ];


const GraficoVentasPorDia: React.FC = () => {

    return (
        <LineChart
            width={500}
            height={300}
            data={data}
            margin={{
              top: 5, right: 30, left: 20, bottom: 5,
            }}
        >
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="fecha" />
            <YAxis />
            <Tooltip />
            <Legend />
            <Line type="monotone" dataKey="cantidadVentas" stroke="#8884d8" activeDot={{ r: 8 }} />
          </LineChart>
    );
};

export default GraficoVentasPorDia;