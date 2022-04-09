import UserRepository from "../repository/user-repo.js";
import * as httpStatus from "../../config/constants/http-status.js";
import UserException from "../exceptions/user-exception.js";

class UserService {
  async findByEmail(req) {
    try {
      const { email } = req.params;
      this.validateRequestData(email);
      let user = await UserRepository.findByEmail(email);
      this.validateUserNotFound(user);
      return {
        status: httpStatus.SUCESS,
        user: {
          id: user.id,
          name: user.name,
          email: user.email,
        },
      };
    } catch (error) {
      return {
        status: error.status ? error.status : httpStatus.INTERNAL_SERVER_ERROR,
        message: error.message,
      };
    }
  }

  validateRequestData(email) {
    if (!email) {
      throw new UserException(
        httpStatus.BAD_REQUEST,
        "User email was not informed!"
      );
    }
  }

  validateUserNotFound(user) {
    if (!user) {
      throw new UserException(httpStatus.BAD_REQUEST, "User was not found!");
    }
  }
}

export default new UserService();