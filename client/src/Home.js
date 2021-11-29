import React, { useState } from "react";
import {
  Box,
  Button,
  Grid,
  Image,
  Tab,
  Tabs,
  TextInput,
  Form,
  FormField,
} from "grommet";
import { render } from "@testing-library/react";

const theme = {
  global: {
    backgroundColor: "blue",
  },
};

const getFoodData = (foodInput, setItems, showItems) => {
  fetch(`api/food?searchvalue=${foodInput.name}`)
    .then((res) => res.json())
    .then((res) => {
      console.log(res);
      setItems(res.hints);
      showItems(true);
    });
};

export default () => {
  const [value, setValue] = useState({});
  const [items, setItems] = useState([]);
  const [showItems, setShowItems] = useState(false);

  return (
    <Box gridArea="main" background="light-2" align="center">
      <Tabs>
        <Tab title="Search Food">
          <Box
            background="light-5"
            width="large"
            pad={{ horizontal: "xlarge", vertical: "medium" }}
            height="medium"
          >
            <Form
              value={value}
              onChange={(nextValue) => setValue(nextValue)}
              onReset={() => {
                setValue();
                setShowItems(false);
                setItems([]);
              }}
              onSubmit={({ value }) => {
                getFoodData(value, setItems, setShowItems);
              }}
            >
              <FormField name="name" htmlFor="text-input-id" label="Food Name">
                <TextInput id="text-input-id" name="name" />
              </FormField>
              <Box direction="row" gap="medium">
                <Button type="submit" primary label="Submit" />
                <Button type="reset" label="Reset" />
              </Box>
            </Form>
            {/* RESULTS  */}
            {showItems && (
              <div>
                {items.map((item, i) => {
                  console.log("mounted");
                  return (
                    <div key={i}>
                      <Box height="xxsmall" width="xxsmall">
                        <Image fit="cover" src={item.food.image} />
                      </Box>
                      <p>{item.food.label}</p>
                    </div>
                  );
                })}
              </div>
            )}
          </Box>
        </Tab>
        <Tab title="Enter Value">
          <Box />
        </Tab>
      </Tabs>
    </Box>
  );
};
