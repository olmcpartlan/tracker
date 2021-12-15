import React from 'react';
import { Box, Grid, Button, Collapsible, Text } from 'grommet';


export default () => {
  const [open, setOpen] = React.useState(true);
  return (
    <Collapsible open={open}>
      <Box
        background="light-2"
        round="medium"
        pad="medium"
        align="center"
        justify="center"
      >
        <Box gridArea="header" background="brand" >
          <Button primary onClick={() => setOpen(!open)} label="Toggle" />
          <Text>This is a box inside a Collapsible component</Text>
        </Box>
      </Box >
    </Collapsible>
  )
}