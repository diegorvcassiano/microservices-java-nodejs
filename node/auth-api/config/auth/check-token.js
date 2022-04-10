import jwt from "jsonwebtoken";
import { promisify } from "util";

import * as secrets from "../constants/secrets.js";
import * as httpStatus from "../constants/http-status.js";
import AuthException from "./auth-exception.js";

const bearer = "Bearer ";

export default async (req, res, next) => {
  try {
    const { authorization } = req.headers;
    if (!authorization) {
      throw new AuthException(
        httpStatus.UNAUTHORIZED,
        "Access token not informed!"
      );
    }

    let accessToken = authorization;
    if (accessToken.includes(bearer)) {
      accessToken = accessToken.replace(bearer, "");
    }

    const decoded = await promisify(jwt.verify)(
      accessToken,
      secrets.API_SECRET
    );

    req.authUser = decoded.authUser;

    return next();
  } catch (error) {
    return res.status(httpStatus.INTERNAL_SERVER_ERROR).json({
      status: error.status ? error.status : httpStatus.INTERNAL_SERVER_ERROR,
      message: error.message,
    });
  }
};
