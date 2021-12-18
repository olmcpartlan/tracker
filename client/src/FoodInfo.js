import React, { useEffect, useState } from "react";
import NutritionTable from "./NutritionTable";

export default (foodData) => {
  useEffect(() => {
    console.log(foodData);
  }, []);

  return (
    <div className="food-details">
      
      <p>{foodData.foodData.label}</p>
    </div>
  );
};
