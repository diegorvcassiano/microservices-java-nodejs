import express from "express";

import * as db from "./config/db/initial-data.js";

import userRoutes from "./modules/routes/user-routes.js";

const app = express();
const env = process.env;
const PORT = env.PORT || 8080;

db.createInitialData();
app.use(express.json());
app.use(userRoutes);
app.get("/api/status", (req, res) => {
  return res.status(200).json({
    service: "AUTH-API",
    status: "up",
    httpStatus: 200,
  });
});
app.listen(PORT, () => {
  console.info(`Server started successfully at port ${PORT}`);
});
