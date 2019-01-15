package com.edu.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.dao.CardDao;
import com.edu.entity.Card;
import com.edu.services.ICardService;

@Service
@Transactional
public class CardService implements ICardService{

	@Resource
	private CardDao cardDao;

	public CardDao getCardDao() {
		return cardDao;
	}

	public Card findCardById(int id) {
		return cardDao.findCardById(id);
	}

	public int save(Card card) {
		return cardDao.saveCard(card);
	}

	public List<Card> findCardByPage(String name, String flag, int page, int size) {
		return cardDao.findCardByPage(name, flag, page, size);
	}

	public int deleteBatchCard(int... ids) {
		return cardDao.deleteCardList(ids);
	}

	public int moveBatchCard(String flag,int id) {
		return cardDao.moveBatchCard(id, flag);
	}

	public int findCardByFlag(String name, String flag) {
		return cardDao.findCardNumber(name, flag);
	}

	public int deleteCard(int id) {
		return cardDao.deleteCard(id);
	}

	public int updateCard(Card card) {
		return cardDao.updateCard(card);
	}

	public List<Card> findCardByCondition(String name, String flag, String condition,
			int page, int size) {
		return cardDao.findConditionByPage(name, flag, condition, page, size);
	}

	public int findCardByConditionAndFlag(String name, String flag, String condition) {
		return cardDao.findConditionNumber(name, flag, condition);
	}

}
