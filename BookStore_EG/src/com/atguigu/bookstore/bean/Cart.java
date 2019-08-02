package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车类
 * @author 93
 *
 */
public class Cart implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 保存购物祥的集合
	 * 	-键就是 购物项图书的id
	 */
	private Map<String,CartItem> map = new LinkedHashMap<String,CartItem>();
	/**
	 * 购物车商品总数量
	 *  -遍历购物车集合  累加count属性值
	 */
	private int totalCount;
	/**
	 * 购物车商品总金额
	 * 	-遍历购物项集合  累加 amount属性值
	 */
	private double totalAmount;
	public Cart() {
		super();
	}
	public Cart(int totalCount, double totalAmount) {
		super();
		this.totalCount = totalCount;
		this.totalAmount = totalAmount;
	}
	public Map<String, CartItem> getMap() {
		return map;
	}
	public int getTotalCount() {
		//初始化totalCount 然后重新计算
		totalCount = 0;
		//遍历购物项集合
		for(CartItem cartItem:getCartItemList()) {
			totalCount+=cartItem.getCount();
		}
		return totalCount;
	}
	public double getTotalAmount() {
		totalAmount = 0;
		BigDecimal bd1 = new BigDecimal(totalAmount+"");
		//遍历购物项集合
		for(CartItem cartItem:getCartItemList()) {
			BigDecimal bd2 = new BigDecimal(cartItem.getAmount()+"");
			bd1 = bd1.add(bd2);
		}
		totalAmount = bd1.doubleValue();
		return totalAmount;
	}
	@Override
	public String toString() {
		return "Cart [map=" + map + ", totalCount=" + getTotalCount() + ", totalAmount=" + getTotalAmount() + "]";
	}
	//1、将map的值转为list集合的方法：   为了页面遍历cartItem 以及 计算时方便
	public List<CartItem> getCartItemList(){
		Collection<CartItem> values = map.values();
		return new ArrayList<CartItem>(values);
	}
	//2、添加图书到购物车中的方法： 点击加入购物车时，默认添加一本，只要将图书的id交给服务器，可以查询到id对应的图书 再转为购物项存到map中
	public void addBook2Cart(Book book) {//图书对象由Servlet接受到请求 根据图书id使用service层查询到后传入
		//图书对象要转为购物项之后再存
		//图书在购物车map中只能对应一个购物项
		CartItem cartItem = map.get(book.getId()+"");//一定要将book的id转为字符串从map中取值
		if(cartItem!=null) {
			//1、如果map中已经存在图书对应的购物项，购物项数量+1即可
			cartItem.setCount(cartItem.getCount()+1);
		}else {
			//2、如果map中没有图书对应的购物项，再将图书转为购物项对象存入，默认数量1
			cartItem = new CartItem(book, 1);
			map.put(book.getId()+"", cartItem);
		}
		//要不要将修改后的购物项重新添加到map中?  不需要
	}
	
	//3、删除指定购物项方法
	public void deleteCartItemByBookId(String bookId) {
		//只要移除map中指定的值即可
		map.remove(bookId);
	}
	//4、更新指定购物项数量的方法
	public void updateCartItemCountByBookId(String bookId , String count) {
		//获取要修改数量的购物项
		CartItem cartItem = map.get(bookId);
		//将数量转为int类型,count由用户提交，可能不是一个数字
		int c = cartItem.getCount();//默认值使用购物项之前的正确的数量
		try {
			c = Integer.parseInt(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//修改数量
		cartItem.setCount(c);
	}
	
	//5.清空购物车
	public void clearCart() {
		map.clear(); 
	}
	
	
}
