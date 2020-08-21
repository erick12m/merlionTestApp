import React from "react";
import {ResponsiveContainer, BarChart, CartesianGrid, XAxis, YAxis, Tooltip, Legend, Bar
} from "recharts";

// TODO parsear la data directamente desde la API
const data = [
  {
    "name": "actuating THX",
    "ingresos": 8294,
    "ventas": 7
  },
  {
    "name": "reboot deposit Sri Lanka Rupee",
    "ingresos": 86953,
    "ventas": 4
  },
  {
    "name": "navigating Metical",
    "ingresos": 43291,
    "ventas": 4
  },
  {
    "name": "Internal compressing",
    "ingresos": 55080,
    "ventas": 4
  },
  {
    "name": "cross-platform support",
    "ingresos": 98666,
    "ventas": 3
  }
]

const GraficoTop5VentasProductos: React.FC = () => {
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
          <XAxis unit=" ventas" type="number" width={35} axisLine={false} tickLine={false} />
          <Tooltip
            cursor={false}
          />
          <Bar dataKey="ventas" fill="#3066BE" unit=" ventas" name="Ventas" />
        </BarChart>
      </ResponsiveContainer>
    
  );
};

export default GraficoTop5VentasProductos;