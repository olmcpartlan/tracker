import React, { useEffect, useState } from "react";
import { Box, Grid, Image } from "grommet";
import { List } from "grommet/components/List";
import "./styles.css";
import FoodInfo from "./FoodInfo";

export default (item) => {
  item = item["item"];

  const [hasImage, setHasImage] = useState(false);

  useEffect(() => {
    setHasImage(item.food.image !== null);
  }, []);

  return (
    <div>
      {/* Create the grid for showing food image and properties. */}
      <Grid
        rows={["small", "xsmall", "xsmall"]}
        columns={["small", "small"]}
        gap="medium"
        areas={[
          { name: "image", start: [0, 0], end: [0, 0] },
          { name: "footer", start: [0, 1], end: [0, 1] },
          { name: "content", start: [1, 0], end: [1, 1] },
        ]}
      >
        {/* If the food had an image, show. otherwise just show the name. */}
        {hasImage ?
          <div>
            <Box gridArea="image" background="brand">
              <Image fill src={item.food.image} />
            </Box>
            <Box gridArea="footer" background="light-1">
              <FoodInfo foodData={item.food} />
            </Box>
          </div>
          :
          <Box gridArea="image" background="light-1">
            <p>{item.food.label}</p>
          </Box>
        }
        <Box gridArea="content" background="brand">
          <List
            primaryKey="name"
            secondaryKey="value"
            data={[
              { name: "brand", value: item.food.brand },
              { name: "serving size", value: item.food.servingSize },
              { name: "servings per container", value: item.food.servingsPerContainer },
            ]}
          />
        </Box>
      </Grid>
    </div>
  );
};
