import Sequelize from "sequelize";

const sequelize = new Sequelize("auth-db", "admin", "123456", {
  host: "127.0.0.1",
  dialect: "postgres",
  quoteIndentifiers: false,
  define: {
    syncOnAssociation: true,
    timestamps: false,
    underscored: true,
    underscoredAll: true,
    freezeTableName: true,
  },
});

sequelize
  .authenticate()
  .then(() => {
    console.info("Connection to Postgres stablished!");
  })
  .catch((err) => {
    console.error("Unable to connect to Postgres DB!");
    console.error(err.message);
  });

export default sequelize;
