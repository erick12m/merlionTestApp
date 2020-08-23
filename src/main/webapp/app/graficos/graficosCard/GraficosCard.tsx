import * as React from 'react';

import './GraficosCard.scss';

interface GraficosCardProps extends React.HTMLProps<HTMLDivElement> {
  heading: string
}

const GraficosCard: React.FC<GraficosCardProps> = ({ heading, children, ...otherProps }) => {
  return (
    <div className="card" {...otherProps}>
      <h2>{heading}</h2>
      {children}
    </div>
  );
};

export default GraficosCard;