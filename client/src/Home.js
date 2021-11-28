import React, { useState } from 'react';
import { Box, Button, Grid, Tab, Tabs, TextInput, Form, FormField } from 'grommet';
import { render } from '@testing-library/react';

const theme = {
  global: {
    backgroundColor: 'blue'
  },
};

const getFoodData = () => {
  fetch("/api/")
    .then(res => res.json())
    .then(res => {
      console.log(res);
    })
}

export default () => {
  const [value, setValue] = React.useState({});
  return(
    <Box gridArea="main" background="light-2" align="center">
      <Tabs>
        <Tab title="Search Food" >
          <Box background="light-5" width="large" pad={{horizontal: "xlarge", vertical: "medium"}} height="medium">
            <Form
              value={value}
              onChange={nextValue => setValue(nextValue)}
              onReset={() => setValue({})}
              onSubmit={({ value }) => { }}
            >
              <FormField name="name" htmlFor="text-input-id" label="Food Name">
                <TextInput id="text-input-id" name="name" />
              </FormField>
              <Box direction="row" gap="medium">
                <Button type="submit" primary label="Submit" />
                <Button type="reset" label="Reset" />
              </Box>
            </Form>
          </Box>

        </Tab>
        <Tab title="Enter Value">
          <Box />

        </Tab>
      </Tabs>

    </Box>
  )
}

