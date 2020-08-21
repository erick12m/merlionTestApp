import React from "react";
import {ResponsiveContainer, BarChart, CartesianGrid, XAxis, YAxis, Tooltip, Legend, Bar
} from "recharts";

// TODO parsear la data directamente desde la API
const data = [
    {
      "name": "cross-platform support",
      "ingresos": 98666,
      "ventas": 3
    },
    {
      "name": "reboot deposit Sri Lanka Rupee",
      "ingresos": 86953,
      "ventas": 4
    },
    {
      "name": "Assistant",
      "ingresos": 74211,
      "ventas": 2
    },
    {
      "name": "Internal compressing",
      "ingresos": 55080,
      "ventas": 4
    },
    {
      "name": "Generic Steel Tuna",
      "ingresos": 53676,
      "ventas": 2
    }
  ]

const GraficoTop5IngresosProductos: React.FC = () => {
  return (
    
      <ResponsiveContainer 
      width={500}
      height={300}
      >
        <BarChart 
        data={data}
        fontSize={14}
        layout="vertical"
        >
          <CartesianGrid
            vertical={false}
            stroke="#d6d9da"
            strokeDasharray="3 3"
          />
          <YAxis
            type="category"
            dataKey="name"
            tickLine={false}
          />
          <XAxis unit="$" type="number" width={35} axisLine={false} tickLine={false} />
          <Tooltip
            cursor={false}
          />
          <Bar dataKey="ingresos" fill="#3066BE" unit="$" name="Ingresos" />
        </BarChart>
      </ResponsiveContainer>
    
  );
};

export default GraficoTop5IngresosProductos;