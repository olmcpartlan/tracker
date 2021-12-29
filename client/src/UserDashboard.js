
import React, { useEffect, useState } from "react"



export default () => {
  useEffect(() => {
    const sessionId = window.sessionStorage.getItem("userId");
    fetch(`/food/details?userId=${sessionId}`)
      .then(res => res.json())
      .then(res => {
        console.log(res);
      })
  }, [])

  return (
    <div>
      <p>somethign</p>
    </div>
  );

}