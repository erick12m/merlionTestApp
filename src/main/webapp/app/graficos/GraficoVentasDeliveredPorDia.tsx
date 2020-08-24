import React, { Component } from 'react';
import {
  LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer,
} from 'recharts';

import axios from 'axios';
import GraficosCard from "./graficosCard/GraficosCard"

const apiUrl = 'api/sales/delivered';

export default class GraficoVentasDeliveredPorDia extends React.Component {

  state = {
    data: [],
  };

  componentDidMount() {
    axios.get(`${apiUrl}`)
      .then(res => {
        this.setState({ data: res.data });
      })
  }
  render() {
    return (
      <GraficosCard heading="Ventas enviadas por dia">
        <ResponsiveContainer
          width="100%"
          height={300}
        >
          <LineChart
            width="100%"
            height={300}
            data={this.state.data}
            margin={{
              top: 5, right: 30, left: 20, bottom: 5,
            }}
          >
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="fecha" />
            <YAxis 
              width={35}
            />
            <Tooltip />
            <Legend />
            <Line 
              type="monotone" 
              dataKey="cantidadVentas"
              stroke="#2A6A9E"
              name="Ventas Realizadas"
              activeDot={{ r: 8 }} 
            />
          </LineChart>
        </ResponsiveContainer>
      </GraficosCard>
    );
  }
}

