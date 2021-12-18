import React from 'react';
import { Grommet, Box, Grid } from 'grommet';
import Home from './Home';

const theme = {
  global: {
    font: {
      family: 'Roboto',
      size: '18px',
      height: '20px',
    },
  },
};

export default () => (
  <Grommet theme={theme} >
    <Grid
      rows={['xsmall', 'large']}
      justifyContent={"center"}
      columns={['small', 'xlarge']}
      gap="medium"
      areas={[
        { name: 'header', start: [0, 0], end: [1, 0] },
        { name: 'nav', start: [0, 1], end: [0, 1] },
        { name: 'main', start: [1, 1], end: [1, 1] },
      ]}
    >
      <Box gridArea="header" background="brand" />
      <Box gridArea="nav" background="light-5" />
      <Home />
    </Grid>
  </Grommet>
);