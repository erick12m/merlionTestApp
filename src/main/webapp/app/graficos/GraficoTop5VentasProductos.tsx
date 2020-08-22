import React from "react";
import {
  ResponsiveContainer, BarChart, CartesianGrid, XAxis, YAxis, Tooltip, Legend, Bar
} from "recharts";
import axios from 'axios';

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
      <ResponsiveContainer
        width={500}
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
          <Bar dataKey="ventas" fill="#3066BE" unit=" ventas" name="Ventas" />
        </BarChart>
      </ResponsiveContainer>

    );
  }
}
