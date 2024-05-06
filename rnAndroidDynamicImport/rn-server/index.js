const express = require("express");
const router = express.Router();

/**
 * 返回对应module信息
 */
router.get("/modules", function(req, res, next) {
  res.json({
    data: [
      {
        name: "module_1"
      },
      {
        name: "module_2"
      }
    ]
  });
});
/**
 * 根据名称返回对应module的zip文件
 */
router.get("/download/:name", function(req, res, next) {
  const name = req.params.name;
  res.download(`static/${name}.zip`);
});

module.exports = router;