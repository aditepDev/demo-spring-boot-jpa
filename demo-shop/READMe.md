## FLOW 

- เมื่อเปิดเข้าเว็บไซต์มาจะแสดงสินค้าเป็น อาหารสัตว์ หลายยี่ห้อรวมกันอยู่ `10 ชิ้น`
- โดยเราจะเป็น`สมาชิกเว็บไซต์`อยู่แล้วและมี Wallet ในระบบ `5,000 wallet`
- มีข้อมูลส่วนตัว `ชื่อ,เบอร์โทรศัพท์,ที่อยู่` ที่เคยกรอกไว้แล้วตอนสมัครสมาชิก
- สินค้าที่เรากำลังหาอยู่คืออาหารแมว ยี่ห้อ canagan ได้ค้นหาว่า `canagan` 
- เจออาหารแมวยี่ห้อ canagan `3 ชิ้น` 3 รสชาติ (ปลา,ไก่,ไก่ง่วง)
- กดเข้าไปดูรายละเอียดสินค้า `canagan รสชาติไก่`
- เว็บเปลี่ยนหน้าและได้แสดงรายละเอียกสินค้าคือ `ชื่อ,รายละเอียด,รูป,ราคา`
- ได้เพิ่มสินค้าเข้าไปใน `ตะกร้าสินค้า` 
- และได้กดเข้าไปดูตะกร้าสินค้า ได้โชว์รายละเอียดสินค้าที่เพิ่มเข้ามา
` จำนวนสินค้า,มูลค่ารวม,[สินค้า (list) ชื่อ,รูป,ราคา,จำนวนที่ซื้อ] `
- ได้กดชำระเงิน โดย  `ที่อยู่จัดส่ง` จะเอามาจากตอนสมัครสมาชิก
- และการชำระเงินก็ `ตัดเงินจาก wallet`
- กดยืนยันการชำระเงิน ระบบได้ตัดเงิน
- และได้โชว์สรุปคำสั่งซื้อ
` รหัสคำสั่งซื้อ, ชื่อคนชำระเงิน, วันที่ทำรายการ, ราคารวม`



## API Design
https://www.tablesgenerator.com/markdown_tables

| Method |        Url        |             Description            |                                                  Sample Valid Request Body                                                 |                                                   Sample Valid Response Body                                                  |
|:------:|:-----------------:|:----------------------------------:|:--------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------:|
|   GET  |       /item       |            แสดงสินค้าทั้งหมด           |                                                              -                                                             |  [JSON](https://github.com/aditepDev/assignment-java-boot-camp/blob/feature/week1/api_design/response_body/show_itemList.md)  |
|   GET  | /item?name={name} |         แสดงสินค้าตามการค้นหา         |                                                              -                                                             | [JSON](https://github.com/aditepDev/assignment-java-boot-camp/blob/feature/week1/api_design/response_body/show_itemSearch.md) |
|   GET  |     /item/{id}    |         แสดงสินค้าตาม id สินค้า        |                                                              -                                                             |    [JSON](https://github.com/aditepDev/assignment-java-boot-camp/blob/feature/week1/api_design/response_body/show_item.md)    |
|  POST  |       /cart       |         เพิ่มสินค้าเข้าตะกร้าสินค้า        |    [JSON](https://github.com/aditepDev/assignment-java-boot-camp/blob/feature/week1/api_design/request_body/add_cart.md)   |                                                               -                                                               |
|   GET  |       /cart       | แสดงสินค้าที่เพิ่มเข้ามาในตะกร้าและมูลค่ารวม |                                                              -                                                             |    [JSON](https://github.com/aditepDev/assignment-java-boot-camp/blob/feature/week1/api_design/response_body/show_cart.md)    |
|  POST  |     /checkout     |        สร้างคำสั่งซื้อและชำระเงิน        | [JSON](https://github.com/aditepDev/assignment-java-boot-camp/blob/feature/week1/api_design/request_body/checkout_cart.md) |  [JSON](https://github.com/aditepDev/assignment-java-boot-camp/blob/feature/week1/api_design/response_body/checkout_cart.md)  |
|   GET  | /bill/{invoiceNo} |             ดูข้อมูลคำสั่งซื้อ            |                                                              -                                                             |       [JSON](https://github.com/aditepDev/assignment-java-boot-camp/blob/feature/week1/api_design/response_body/bill.md)      | 
|        |                   |                                    |                                                                                                                            |   


[ref](https://github.com/up1/course_microservices-3-days/blob/master/slide/01-DESIGN-MICROSERVICE-WORKSHOP.pdf)
