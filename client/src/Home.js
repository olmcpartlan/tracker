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
  Paragraph,
  InfiniteScroll,
} from "grommet";
import { render } from "@testing-library/react";
import FoodItem from "./FoodItem";

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
            height="large"
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
              <FormField name="name" htmlfor="text-input-id" label="food name"/>

              <Box direction="row" gap="medium">
                <Button type="submit" primary label="Submit" />
                <Button type="reset" label="Reset" />
              </Box>
            </Form>
            {/* RESULTS  */}
            {showItems && (
              <Box overflow="auto" >
                <InfiniteScroll pad="medium" step="1" items={items}>
                  {(item, i) => <FoodItem key={i} item={item} />}
                </InfiniteScroll>
              </Box>
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
