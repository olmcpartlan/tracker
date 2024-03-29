import React, { useEffect, useState } from "react";
import { Box, Grid, Image, Table, TableBody, TableRow, TableCell, Button, Tip } from "grommet";
import { List } from "grommet/components/List";
import "./styles.css";
import FoodInfo from "./FoodInfo";
import { CircleInformation, Clear, Info } from "grommet-icons";

export default (item, userLoggedIn) => {
  item = item["item"];

  const [hasImage, setHasImage] = useState(false);
  const [showAddFood, setShowAddFood] = useState(false);

  useEffect(() => {
    console.log(userLoggedIn);
    setHasImage(item.food.image !== null);
    showAddButton();
  }, []);


  const showAddButton = () => {
    const isLoggedIn = window.sessionStorage.getItem("userId") !== 'undefined';

    console.log(isLoggedIn);
    setShowAddFood(isLoggedIn);
  }



  const addFood = (foodId) => {
    const userId = window.sessionStorage.getItem("userId");
    fetch(`food/add?userId=${userId}&foodId=${foodId}`)
      .then(res => res.text())
      .then(res => {
        console.log(res);
      })
  }

  const cleanNutrientLabel = (nutrient_label) => {
    let cleanLabel = "";
    switch (nutrient_label) {
      case "CA": cleanLabel = "Calcium (mg)"; break;
      case "ENERC_KCAL": cleanLabel = "Energy (kcal)"; break;
      case "PROCNT": cleanLabel = "Protein (g)"; break;
      case "FAT": cleanLabel = "Fat (g)"; break;
      case "FAMS": cleanLabel = "Monounsaturated Fatty Acids (g)"; break;
      case "FAPU": cleanLabel = "Polyunsaturated Fatty Acids (g)"; break;
      case "CHOCDF": cleanLabel = "Carbohydrates (g)"; break;
      case "CHOLE": cleanLabel = "Cholesterol (mg)"; break;
      case "FIBTG": cleanLabel = "Fiber (g)"; break;
      case "FASAT": cleanLabel = "Saturated Fatty Acids (g)"; break;
      case "FATRN": cleanLabel = "Total fatty acids (g)"; break;
      case "FE": cleanLabel = "Iron (mg)"; break;
      case "FOLDFE": cleanLabel = "Folate (µg)"; break;
      case "K": cleanLabel = "Potassium (mg)"; break;
      case "MG": cleanLabel = "Magnesium (mg)"; break;
      case "NA": cleanLabel = "Sodium (mg)"; break;
      case "P": cleanLabel = "Phosphorus (mg)"; break;
      case "RIBF": cleanLabel = "Riboflavin (mg)"; break;
      case "SUGAR": cleanLabel = "Sugar (g)"; break;
      case "THIA": cleanLabel = "Thiamin (g)"; break;
      case "TOCPA": cleanLabel = "Vitamin E (mg)"; break;
      case "VATA_RAE": cleanLabel = "Vitamin A (µg)"; break;
      case "VITB12": cleanLabel = "Vitamin B12 (µg)"; break;
      case "VITB6A": cleanLabel = "Vitamin B-6 (mg)"; break;
      case "VITC": cleanLabel = "Vitamin C (mg)"; break;
      case "VITD": cleanLabel = "Vitamin D (mg)"; break;
      case "VITKe": cleanLabel = "Vitamin K (µg)"; break;
      case "ZN": cleanLabel = "Zinc (mg)"; break;
    }

    return cleanLabel;

  }

  return (
    <div>
      {/* Create the grid for showing food image and properties. */}
      <Grid
        rows={["small", "flex", "xsmall"]}
        columns={["xsmall", "medium"]}
        gap="medium"
        areas={[
          { name: "image", start: [0, 0], end: [0, 0] },
          { name: "footer", start: [0, 1], end: [0, 1] },
          { name: "content", start: [1, 0], end: [1, 1] },
        ]}
      >
        <div>
          <Box gridArea="image" background="brand">
            {hasImage
              ? <Image fill src={item.food.image} />
              : <Clear size="xlarge" />
            }
            <p>{item.food.brand}</p>
            <Box className="footer-container">
            </Box>
          </Box>
          <Box gridArea="footer" >
          </Box>
        </div>
        <Box gridArea="content" background={{ dark: 'dark-2', light: 'light-2' }}>
          <Table>
            <TableBody>
              <TableRow className="brand-cell" >
                <TableCell size="medium" align="center">
                  <h3>{item.food.label}</h3>
                </TableCell>
                <TableCell>
                  {!showAddFood
                    ? <Tip content="Please log in to save food data.">
                      <CircleInformation />
                    </Tip>
                    : <Box>
                      <Button
                        style={{ padding: '10px' }}
                        label="add"
                        primary
                        onClick={() => {
                          console.log(item.food.foodId)
                          addFood(item.food.foodId)
                        }}
                      />
                    </Box>

                  }

                </TableCell>

              </TableRow>
              {/* Map the nutrient values for the rendered food. */}
              {Object.entries(item.food.nutrients).map((nutrient, i) => {
                // A significant amount of nutrients will come back null.
                // Only showing properties that have a value for nutrients.
                if (nutrient[1] !== null) return <TableRow key={i}>
                  <TableCell scope="row">
                    <strong>{cleanNutrientLabel(nutrient[0])}</strong>
                  </TableCell>
                  <TableCell scope="row">
                    {/* some values go to 10+ digits */}
                    <p>{Number.parseFloat(nutrient[1]).toFixed(3)}</p>
                  </TableCell>
                </TableRow>
              })}
              {item.food.servingSizes !== null &&
                <div>
                  <TableRow>
                    <TableCell scope="col">
                      Serving Sizes:
                    </TableCell>

                    {item.food.servingSizes.map((size, i) => {
                      return <TableRow >
                        <TableCell
                          scope="col"
                          align='right'
                        >
                          {Number.parseFloat(size.quantity).toFixed(3)}
                        </TableCell>
                        <TableCell scope="col" >{size.label}   </TableCell>
                      </TableRow>
                    })}
                  </TableRow>
                </div>
              }

            </TableBody>
          </Table>
        </Box>
      </Grid>
    </div>
  );
};

