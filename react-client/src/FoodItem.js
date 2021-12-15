import React, { useEffect, useState } from "react";
import { Box, Grid, Image } from "grommet";
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
      {hasImage ? (
        <Grid
          rows={["small", "xsmall", "xsmall"]}
          columns={["small", "medium"]}
          gap="medium"
          areas={[
            { name: "image", start: [0, 0], end: [0, 0] },
            { name: "footer", start: [0, 1], end: [0, 1] },
            { name: "content", start: [1, 0], end: [1, 1] },
          ]}
        >
          <Box gridArea="image" background="brand">
            <Image fill src={item.food.image} />
          </Box>
          <Box gridArea="footer" background="light-1">
						<FoodInfo foodData={item.food} />
          </Box>
          <Box gridArea="content" background="brand" >
            <p>{item.food.label}</p>
					</Box>

        </Grid>
      ) : (
        <Grid
          rows={["xsmall", "small", "xxsmall"]}
          columns={["small", "medium"]}
          gap="medium"
          areas={[
            { name: "footer", start: [0, 0], end: [0.5, 0] },
            { name: "content", start: [1, 0], end: [1, 1] },
          ]}
        >
          <Box gridArea="footer" background="light-1" >
						<p>{item.food.label}</p>
					</Box>
          <Box gridArea="content" background="brand" />
        </Grid>
      )}
    </div>
  );
};
