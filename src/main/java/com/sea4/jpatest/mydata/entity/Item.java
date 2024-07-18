package com.sea4.jpatest.mydata.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Builder @Data @Getter @Setter @ToString
@Entity @Table(name = "ITEM", catalog = "MY_DATA")
@AllArgsConstructor @NoArgsConstructor
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITEM_NO")
	private Long itemNo;

	@Column(name = "ITEM_CD", length = 50)
	private String itemCd;

	@Column(name = "ITEM_NM", length = 100)
	private String itemNm;

	@Column(name = "ITEM_AMT")
	private Long itemAmt;

	@Column(name = "ITEM_QTY")
	private Long itemQty;

	@Column(name = "ITEM_STATUS", length = 10)
	private String itemStatus;

	public static Item of(
		final String itemCd,
		final String itemNm,
		final Long itemAmt,
		final Long itemQty,
		final String itemStatus) {
		return Item.builder()
			.itemCd(itemCd)
			.itemNm(itemNm)
			.itemAmt(itemAmt)
			.itemQty(itemQty)
			.itemStatus(itemStatus)
			.build();
	}
}
