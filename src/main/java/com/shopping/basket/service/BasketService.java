package com.shopping.basket.service;

import com.shopping.basket.dao.IBasketDao;
import com.shopping.basket.dto.BasketDTO;
import com.shopping.basket.dto.BasketItemDTO;
import com.shopping.basket.entity.BasketEntity;
import com.shopping.basket.entity.BasketItemEntity;
import com.shopping.basket.implementation.IBasketService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service("basketService")
public class BasketService implements IBasketService {

    @Autowired
    private IBasketDao basketDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public BasketDTO getBasketByBasketId(Integer basketId){
        BasketEntity  basketEntity = basketDao.findById(basketId).orElse(null);

        if(basketEntity == null) return null;

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        BasketDTO basketDTO = modelMapper.map(basketEntity, BasketDTO.class);

        return basketDTO;
    }

    @Override
    @Transactional
    public List<BasketDTO> getBasketsByCustomerId(Integer customerId){
        List<BasketEntity> basketEntityList = basketDao.findByCustomerId(customerId);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<BasketDTO> basketDTOList = basketEntityList.stream().map(entity -> modelMapper.map(entity, BasketDTO.class)).collect(Collectors.toList());

        return basketDTOList;
    }

    @Override
    @Transactional
    public  HashMap<String, Integer> getSumOfAllItemsInAllBasketsByCustomerId(Integer customerId){
        List<BasketDTO> basketDTOList = getBasketsByCustomerId(customerId);
        Integer overallTotal =  basketDTOList.stream().mapToInt(dto -> dto.getItems().stream().mapToInt(item -> item.getPrice()).sum()).sum();
        HashMap<String, Integer> basketWrapper = new HashMap<>();

        for(BasketDTO basketDTO : basketDTOList){
            Integer basketTotal = basketDTO.getItems().stream().mapToInt(item -> item.getPrice()).sum();
            basketWrapper.put("Basket ID -> " + basketDTO.getBasketId().toString(), basketTotal);
        }

        basketWrapper.put("Overall Total", overallTotal);

        return basketWrapper;
    }

    @Override
    @Transactional
    public BasketDTO addBasket(BasketDTO basketDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        BasketEntity basketEntity = modelMapper.map(basketDTO, BasketEntity.class);
        List<BasketItemEntity> basketItemEntityList = basketDTO.getItems().stream().map(dto -> modelMapper.map(dto, BasketItemEntity.class)).collect(Collectors.toList());

        for(BasketItemEntity basketItemEntity: basketItemEntityList){
            basketItemEntity.setBasket(basketEntity);
        }

        basketEntity.setItems(basketItemEntityList);

        basketEntity = basketDao.saveAndFlush(basketEntity);

        basketDTO = modelMapper.map(basketEntity, BasketDTO.class);

        return basketDTO;
    }

    @Override
    @Transactional
    public BasketDTO updateBasket(BasketDTO basketDTO) {
        BasketEntity basketEntity = basketDao.findById(basketDTO.getBasketId()).orElse(null);

        if(basketEntity == null) return null;

        Integer numberOfBasketItemsInDTO = basketDTO.getItems() != null ? basketDTO.getItems().size() : 0;
        Integer numberOfBasketItemsInEntity = basketEntity.getItems().size();

        if(numberOfBasketItemsInDTO == 0){
            if(basketEntity.getItems().size() > 0) basketEntity.getItems().clear();
        } else {
            if(numberOfBasketItemsInDTO > numberOfBasketItemsInEntity){
                for(BasketItemDTO basketItemDTO: basketDTO.getItems()){
                    if(basketItemDTO.getBasketItemId() == null){
                        BasketItemEntity basketItemEntityToAdd = modelMapper.map(basketItemDTO, BasketItemEntity.class);
                        basketItemEntityToAdd.setBasket(basketEntity);

                        basketEntity.getItems().add(basketItemEntityToAdd);
                    }
                }
            } else if(numberOfBasketItemsInEntity > numberOfBasketItemsInDTO){
                List<Integer> uniqueItemIds = basketDTO.getItems().stream().map(item -> item.getBasketItemId()).collect(Collectors.toList());

                basketEntity.getItems().removeIf(item -> !uniqueItemIds.contains(item.getBasketItemId()));
            }

            for(BasketItemEntity basketItemEntity : basketEntity.getItems()){
                for(BasketItemDTO basketItemDTO: basketDTO.getItems()){
                    if(basketItemEntity.getBasketItemId() != null && basketItemDTO.getBasketItemId() != null){
                        if(basketItemEntity.getBasketItemId() == basketItemDTO.getBasketItemId()){
                            basketItemEntity.setName(basketItemDTO.getName());
                            basketItemEntity.setPrice(basketItemDTO.getPrice());
                            basketItemEntity.setQuantity(basketItemDTO.getQuantity());
                        }
                    }
                }
            }
        }

        basketEntity = basketDao.saveAndFlush(basketEntity);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        basketDTO = modelMapper.map(basketEntity, BasketDTO.class);

        return basketDTO;
    }

    @Override
    @Transactional
    public boolean deleteBasketByBasketId(Integer basketId){
        BasketEntity basketEntity = basketDao.findById(basketId).orElse(null);

        if(basketEntity == null) return false;

        basketDao.deleteById(basketId);

        return true;
    }
}
