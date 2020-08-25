import React from "react";
import {
  ResponsiveContainer, BarChart, CartesianGrid, XAxis, YAxis, Tooltip, Bar
} from "recharts";
import axios from 'axios';
import GraficosCard from './graficosCard/GraficosCard';

const apiUrl = 'api/sales/more-selled';

export default class GraficoTop5VentasProductos extends React.Component {

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
      <GraficosCard heading="Productos más vendidos">
        <ResponsiveContainer
          width="100%"
          height={300}
        >
          <BarChart
            data={this.state.data}
            fontSize={14}
            layout="vertical"
          >
            <CartesianGrid
              vertical={false}
              stroke="#d6d9da"
              strokeDasharray="3 3"
            />
            <YAxis
              width={180}
              type="category"
              dataKey="name"
              tickLine={false}
              scaleToFit={true}
            />
            <XAxis 
              width={180}
              unit=" ventas" 
              type="number" 
              axisLine={false} 
              tickLine={false} 
              scaleToFit={true}
            />
            <Tooltip
              cursor={false}
            />
            <Bar 
              dataKey="ventas" 
              fill="#2A6A9E" 
              name="Ventas"
              barSize={30} 
            />
          </BarChart>
        </ResponsiveContainer>
      </ GraficosCard>

    );
  }
}
