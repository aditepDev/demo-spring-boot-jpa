GET : /cart

status 200 OK

```json
{ 
  "user_detail": {
    "user_name": "test test",
    "user_address": "93 ม.3 ต.บ้านเลื่อม อ.เมือง จ.อุดรธานี 41000",
    "user_tel": "0800000000"
  },"payment" : {
    "wallet" : 5000
  },
  "item_list": [
    {
      "item_id": 2,
      "item_name": "FRISKIES Seafood & Surfin Turfin ฟริสกี้ส์ อาหารแมว อาหารเม็ด สำหรับแมวโต รสซีฟู้ด และ รสรวมมิตรปลาทะเล 19 กก",
      "item_description": "เฟริสกี้ส์ ซีฟู้ด เซิร์ฟฟิ่ง แอนด์ เทิร์ฟฟิ่ง เฟเวอร์ริท อาหารเม็ดสำหรับแมวโตทุกสายพันธุ์ รสปลาทูน่าและซาร์ดีน ขนาด 19 กิโลกรัม",
      "item_image": "friskies.jpg",
      "item_price": 1272,
      "item_rating": 4.3,
      "qty": 2
    }
  ],
  "checkout": {
    "total_qty" : 2,
    "total_price" : 2544
  }
}
```