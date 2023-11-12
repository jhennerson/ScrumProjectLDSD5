const PROXY_CONFIG = [
  {
    context: ["/api"],
    target: "http://localhost:8080/",
    secure: true,
    logLevel: "debug",
  },
];

module.exports = PROXY_CONFIG;
