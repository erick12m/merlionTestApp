import React from "react";
import {
  ResponsiveContainer, BarChart, CartesianGrid, XAxis, YAxis, Tooltip, Legend, Bar
} from "recharts";
import axios from 'axios';
import GraficosCard from './graficosCard/GraficosCard';

const apiUrl = 'api/sales/more-entry';

export default class GraficoTop5IngresosProductos extends React.Component {

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
      <GraficosCard heading="Productos con más beneficios">
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
            <XAxis unit="$" type="number" width={35} axisLine={false} tickLine={false} />
            <Tooltip
              cursor={false}
            />
            <Bar dataKey="ingresos" fill="#2A6A95" unit="$" name="Ingresos" />
          </BarChart>
        </ResponsiveContainer>
      </GraficosCard>
    );
  }
}

