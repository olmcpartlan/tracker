import React, { useEffect, useState } from "react";
import { NutritionLabel } from "react-fda-nutrition-facts";
import './styles.css'

export default () => {
  return (
    <div className="nutrition-label">
      <img src="https://s.cdpn.io/3/NutritionFacts.gif" class="image" />

      <section class="performance-facts">

        <table class="performance-facts__table--grid">
          <tbody>
            <tr>
              <td colspan="2">Vitamin A 10%</td>
              <td>Vitamin C 0%</td>
            </tr>
            <tr class="thin-end">
              <td colspan="2">Calcium 10%</td>
              <td>Iron 6%</td>
            </tr>
          </tbody>
        </table>

        <p class="small-info">
          * Percent Daily Values are based on a 2,000 calorie diet. Your daily
          values may be higher or lower depending on your calorie needs:
        </p>

        <table class="performance-facts__table--small small-info">
          <thead>
            <tr>
              <td colspan="2"></td>
              <th>Calories:</th>
              <th>2,000</th>
              <th>2,500</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th colspan="2">Total Fat</th>
              <td>Less than</td>
              <td>65g</td>
              <td>80g</td>
            </tr>
            <tr>
              <td class="blank-cell"></td>
              <th>Saturated Fat</th>
              <td>Less than</td>
              <td>20g</td>
              <td>25g</td>
            </tr>
            <tr>
              <th colspan="2">Cholesterol</th>
              <td>Less than</td>
              <td>300mg</td>
              <td>300 mg</td>
            </tr>
            <tr>
              <th colspan="2">Sodium</th>
              <td>Less than</td>
              <td>2,400mg</td>
              <td>2,400mg</td>
            </tr>
            <tr>
              <th colspan="3">Total Carbohydrate</th>
              <td>300g</td>
              <td>375g</td>
            </tr>
            <tr>
              <td class="blank-cell"></td>
              <th colspan="2">Dietary Fiber</th>
              <td>25g</td>
              <td>30g</td>
            </tr>
          </tbody>
        </table>

        <p class="small-info">Calories per gram:</p>
        <p class="small-info text-center">
          Fat 9 &bull; Carbohydrate 4 &bull; Protein 4
        </p>
      </section>
    </div>
  );
};
