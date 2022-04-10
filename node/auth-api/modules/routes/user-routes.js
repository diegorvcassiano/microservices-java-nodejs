import { Router } from "express";

import UserController from "../controller/user-controller.js";
import checkToken from "../../config/auth/check-token.js";

const router = new Router();

// Public requests. (Do  not need the authorization)
router.post("/api/user/auth", UserController.getAccessToken);

// Protected API. Need authorization
router.use(checkToken); // everything below this request will deman authorization
router.get("/api/user/email/:email", UserController.findByEmail);

export default router;
