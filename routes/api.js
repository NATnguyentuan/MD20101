const express = require("express");
const router = express.Router();
const Fruits = require("../model/fruits");
const Users = require("../model/user");
const Distributor = require("../model/distibutor");
//thêm distributor
router.post("/add_fruit", async (req, res) => {
    //lấy dữ liệu
    const data = req.body;
    //tạo đối tượng distributor
    const newFruits = new Fruits({
        name: data.name,
        quantity: data.quantity,
        price: data.price,
        status: data.status,
        image: data.image,
        description: data.description,
        
    });
    const result = await newFruits.save();

    if (result) {
        res.json({
            status: 200,
            message: "add succesfull",
            data: result
        });
    } else {
        res.json({
            status: 400,
            message: "add succesfull",
            data: "error"
        });
    }
});
router.get("/get-list-fruit", async (req, res) => {
    try{
        const data  = await Fruits.find().populate('id_distributor');
        res.json({
            "status": 200,
            "messenger": "Danh sach fruit", 
            "data" : data
        })
    }catch(error){
        console.log(error)
    }
});
// router.get("/get-list-fruit-by-id", async (req, res) => {
//     try{
//         const {id} =req.params
//         const data  = await Fruits.findById(id).populate('id_distributor');
//         res.json({
//             "status": 200,
//             "messenger": "Danh sach fruit", 
//             "data" : data
//         })
//     }catch(error){
//         console.log(error)
//     }
// });
// router.get("/get-list-fruit-in-price", async (req, res) => {
//     try {
//         const { price_start, price_end } = req.query;

//         // Chuyển đổi price_start và price_end sang kiểu số để so sánh chính xác
//         const priceStart = Number(price_start);
//         const priceEnd = Number(price_end);

//         // Tạo query tìm kiếm theo khoảng giá
//         const query = { price: { $gte: priceStart, $lte: priceEnd } };

//         // Thực hiện truy vấn
//         const data = await Fruits.find(query, 'name quantity price id_distributor')
//             .populate('id_distributor')  // Sửa lỗi chính tả ở đây
//             .sort({ quantity: -1 })
//             .skip(0)
//             .limit(2);

//         // Trả về kết quả
//         res.json({
//             status: 200,
//             message: "Danh sách fruit",
//             data: data
//         });
//     } catch (error) {
//         console.error(error);
//         res.status(500).json({ status: 500, message: "Đã xảy ra lỗi!" });
//     }
// });
// router.get("/get-list-fruit-have-name-a-or-x", async (req, res) => {
//     try {
//         // Tạo query tìm kiếm theo khoảng giá
//         const query = {$or:[
//             {name:{$regex:'T'}},
//             {name:{$regex:'X'}},
//         ]}

//         // Thực hiện truy vấn
//         const data = await Fruits.find(query, 'name quantity price id_distributor')
//             .populate('id_distributor')  // Sửa lỗi chính tả ở đây

//         // Trả về kết quả
//         res.json({
//             status: 200,
//             message: "Danh sách fruit",
//             data: data
//         });
//     } catch (error) {
//         console.error(error);
//         res.status(500).json({ status: 500, message: "Đã xảy ra lỗi!" });
//     }
// });

router.put("/update-fruit-by-id/:id", async (req, res) => {
    try {
        const { id } = req.params; // Lấy id đúng cách
        const data = req.body;

        // Tìm fruit theo ID
        const updatefruit = await Fruits.findById(id);
        if (!updatefruit) {
            return res.status(404).json({ status: 404, message: "Không tìm thấy sản phẩm" });
        }

        // Cập nhật thông tin chỉ nếu giá trị mới hợp lệ
        if (typeof data.name !== "undefined") updatefruit.name = data.name;
        if (typeof data.quantity !== "undefined") updatefruit.quantity = Number(data.quantity);
        if (typeof data.price !== "undefined") updatefruit.price = Number(data.price);
        if (typeof data.status !== "undefined") updatefruit.status = Number(data.status);
        if (typeof data.image !== "undefined") updatefruit.image = data.image;
        if (typeof data.description !== "undefined") updatefruit.description = data.description;
        if (typeof data.id_distributor !== "undefined") updatefruit.id_distributor = data.id_distributor;

        // Lưu cập nhật
        const result = await updatefruit.save();

        return res.json({
            status: 200,
            message: "Cập nhật thành công",
            data: result,
        });
    } catch (error) {
        console.error(error);
        return res.status(500).json({ status: 500, message: "Đã xảy ra lỗi!", error: error.message });
    }
});
//xóa Fruits by id
router.delete("/deleteFruitsById/:id", async (req, res) => {
    try {
        const { id } = req.params; // Lấy ID từ request params
        
        // Kiểm tra nếu ID hợp lệ (MongoDB ObjectId)
        if (!id.match(/^[0-9a-fA-F]{24}$/)) {
            return res.status(400).json({
                status: 400,
                message: "Invalid ID format"
            });
        }

        // Tìm và xóa sản phẩm
        const deletedFruits = await Fruits.findByIdAndDelete(id);

        // Nếu không tìm thấy sản phẩm
        if (!deletedFruits) {
            return res.status(404).json({
                status: 404,
                message: "Fruit not found"
            });
        }

        // Nếu xóa thành công
        return res.status(200).json({
            status: 200,
            message: "Delete success",
            data: deletedFruits
        });

    } catch (error) {
        console.error("Delete error:", error);
        return res.status(500).json({
            status: 500,
            message: "Internal server error",
            error: error.message
        });
    }
});
router.post("/add_distributor", async (req, res) => {
    //lấy dữ liệu
    const data = req.body;
    //tạo đối tượng distributor
    const newDistributor = new Distributor({
        name: data.name,
        
    });
    const result = await newDistributor.save();

    if (result) {
        res.json({
            status: 200,
            message: "add succesfull",
            data: result
        });
    } else {
        res.json({
            status: 400,
            message: "add succesfull",
            data: "error"
        });
    }
});


 

module.exports = router;