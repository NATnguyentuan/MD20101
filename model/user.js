const mongoose = require("mongoose")
const Schema = mongoose.Schema;
const Users = new Schema({
    username: {type:String},
    password: {type:String},
    email:{type:String},
    name:{type:String},
    avatar:{type:String},
    available:{type:Boolean, default:false},
},
{
    timestamps: true
});
module.exports = mongoose.model('user',Users);