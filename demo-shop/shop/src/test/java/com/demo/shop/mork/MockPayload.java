package com.demo.shop.mork;

import com.demo.shop.member.model.Member;
import com.demo.shop.purchase.requests.CheckOutRequest;
import com.demo.shop.purchase.requests.ItemList;
import com.demo.shop.purchase.requests.UserDetail;

import java.util.ArrayList;
import java.util.List;

public class MockPayload {
    public static CheckOutRequest getCheckOutRequest() {
        CheckOutRequest checkOutRequest = new CheckOutRequest();
        List<ItemList> itemLists = new ArrayList<>();
        itemLists.add(getItemList());
        checkOutRequest.setItemList(itemLists);
        checkOutRequest.setUserDetail(getUserDetail());


        return checkOutRequest;

    }

    public static UserDetail getUserDetail() {
        Member member = MockData.getMember();
        UserDetail userDetail = new UserDetail();
        userDetail.setUserName(member.getMemberName());
        userDetail.setUserAddress(member.getMemberAddress());
        userDetail.setUserTel(member.getMemberTel());
        return userDetail;
    }

    public static  ItemList getItemList(){
        ItemList itemList = new ItemList();
        itemList.setItemId(1);
        itemList.setQty(2);
        return itemList;
    }
}