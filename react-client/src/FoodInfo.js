import React, { useEffect, useState } from 'react';
// import { NutritionLabel } from 'react-fda-nutrition-facts';
import NutritionTable from './NutritionTable';

export default (foodData) => {
	useEffect(() => {
		console.log(foodData);
	}, [])


	return (
		<div className="nutrition-label">
			{/* <NutritionLabel */}
			<NutritionTable
			// servingSize={foodData.servingsizes[0].label}
			/>
		</div>
	);
}

// servingSize={'1 cup (228g)'}
// servingsPerContainer={2}
// calories={260}
// totalFat={13}
// saturatedFat={5}
// transFat={2}
// cholesterol={30}
// sodium={660}
// totalCarbs={31}
// dietaryFiber={0}
// sugars={5}
// protein={5}
// vitaminA={4}
// vitaminC={2}
// calcium={15}
// iron={4}