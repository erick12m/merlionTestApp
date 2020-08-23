import React from "react";
import {
  ResponsiveContainer, BarChart, CartesianGrid, XAxis, YAxis, Tooltip, Legend, Bar
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
              type="category"
              dataKey="name"
              tickLine={false}
            />
            <XAxis unit=" ventas" type="number" width={35} axisLine={false} tickLine={false} />
            <Tooltip
              cursor={false}
            />
            <Bar dataKey="ventas" fill="#2A6A9E" unit=" ventas" name="Ventas" />
          </BarChart>
        </ResponsiveContainer>
      </ GraficosCard>

    );
  }
}
