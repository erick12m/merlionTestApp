import React, { PureComponent } from 'react';
import {
  LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend,
} from 'recharts';
import axios from 'axios';

const apiUrl = 'api/sales/all';

export default class GraficoVentasPorDia extends React.Component {

  state = {
    data: [],
  };
  
  componentDidMount(){
    axios.get(`${apiUrl}`)
    .then(res =>{
      this.setState({ data: res.data });
    })
  }
  render() {
    return (
      <LineChart
        width={500}
        height={300}
        data={this.state.data}
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
  }
}
