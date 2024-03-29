/**
 * 
 */
package io.mosip.kernel.masterdata.service.impl;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.mosip.kernel.core.http.ResponseWrapper;
import io.mosip.kernel.masterdata.constant.FoundationalTrustProviderErrorCode;
import io.mosip.kernel.masterdata.dto.FoundationalTrustProviderDto;
import io.mosip.kernel.masterdata.dto.FoundationalTrustProviderPutDto;
import io.mosip.kernel.masterdata.dto.getresponse.FoundationalTrustProviderResDto;
import io.mosip.kernel.masterdata.entity.FoundationalTrustProvider;
import io.mosip.kernel.masterdata.entity.FoundationalTrustProviderHistory;
import io.mosip.kernel.masterdata.exception.MasterDataServiceException;
import io.mosip.kernel.masterdata.repository.FoundationalTrustProviderRepository;
import io.mosip.kernel.masterdata.repository.FoundationalTrustProviderRepositoryHistory;
import io.mosip.kernel.masterdata.service.FoundationalTrustProviderService;
import io.mosip.kernel.masterdata.utils.MetaDataUtils;

/**
 * @author Ramadurai Pandian
 *
 */
@Component
public class FoundationalTrustProviderServiceImpl implements FoundationalTrustProviderService {
	
	@Autowired
	private FoundationalTrustProviderRepository foundationalTrustProviderRepository;
	
	@Autowired
	private FoundationalTrustProviderRepositoryHistory foundationalTrustProviderRepositoryHistory;

	@Override
	@Transactional
	public ResponseWrapper<FoundationalTrustProviderResDto> registerFoundationalTrustProvider(
			FoundationalTrustProviderDto foundationalTrustProviderDto) {
		FoundationalTrustProvider foundationalTrustProvider = null;
		FoundationalTrustProvider foundationalTrustProviderEntity = null;
		FoundationalTrustProviderResDto foundationalTrustProviderResDto = null;
		ResponseWrapper<FoundationalTrustProviderResDto> response = new ResponseWrapper<>();
		foundationalTrustProvider = foundationalTrustProviderRepository.findByDetails(foundationalTrustProviderDto.getName(),foundationalTrustProviderDto.getEmail(),foundationalTrustProviderDto.getAddress(),foundationalTrustProviderDto.getCertAlias(),foundationalTrustProviderDto.isActive());
		if(foundationalTrustProvider!=null)
		{
			throw new MasterDataServiceException(FoundationalTrustProviderErrorCode.FTP_ALREADY_PRESENT.getErrorCode(),FoundationalTrustProviderErrorCode.FTP_ALREADY_PRESENT.getErrorMessage());
		}
		else
		{
			foundationalTrustProvider = MetaDataUtils.setCreateMetaData(foundationalTrustProviderDto, FoundationalTrustProvider.class);
			foundationalTrustProvider.setIsActive(foundationalTrustProviderDto.isActive());	
			foundationalTrustProviderEntity = foundationalTrustProviderRepository.create(foundationalTrustProvider);
			if(foundationalTrustProviderEntity!=null)
			{
				foundationalTrustProviderResDto =  MetaDataUtils.setCreateMetaData(foundationalTrustProviderEntity, FoundationalTrustProviderResDto.class);
				response.setResponse(foundationalTrustProviderResDto);
				response.setResponsetime(LocalDateTime.now());
				FoundationalTrustProviderHistory foundationalTrustProviderHistory = MetaDataUtils.setCreateMetaData(foundationalTrustProviderEntity, FoundationalTrustProviderHistory.class);
				foundationalTrustProviderHistory.setIsActive(foundationalTrustProviderDto.isActive());
				foundationalTrustProviderHistory.setEffectivetimes(foundationalTrustProvider.getCreatedDateTime());
				foundationalTrustProviderHistory.setCreatedDateTime(foundationalTrustProvider.getCreatedDateTime());
				foundationalTrustProviderRepositoryHistory.create(foundationalTrustProviderHistory);
			}
			
		}
			
		return response;
	}

	@Override
	@Transactional
	public ResponseWrapper<FoundationalTrustProviderResDto> updateFoundationalTrustProvider(
			FoundationalTrustProviderPutDto foundationalTrustProviderPutDto) {
		ResponseWrapper<FoundationalTrustProviderResDto> response = new ResponseWrapper<>();
		FoundationalTrustProvider updateFoundationalTrustProvider = new FoundationalTrustProvider();
		FoundationalTrustProviderResDto foundationalTrustProviderResDto = null;
		updateFoundationalTrustProvider = foundationalTrustProviderRepository.findById(FoundationalTrustProvider.class, foundationalTrustProviderPutDto.getId());
		if(updateFoundationalTrustProvider==null)
		{
			throw new MasterDataServiceException(FoundationalTrustProviderErrorCode.ID_NOT_PRESENT.getErrorCode(),FoundationalTrustProviderErrorCode.ID_NOT_PRESENT.getErrorMessage());
		}
		updateFoundationalTrustProvider = MetaDataUtils.setUpdateMetaData(foundationalTrustProviderPutDto, updateFoundationalTrustProvider,  false);
		updateFoundationalTrustProvider.setIsActive(foundationalTrustProviderPutDto.isActive());
		updateFoundationalTrustProvider = foundationalTrustProviderRepository.update(updateFoundationalTrustProvider);
		if(updateFoundationalTrustProvider!=null)
		{
			FoundationalTrustProviderHistory foundationalTrustProviderHistory = MetaDataUtils.setCreateMetaData(updateFoundationalTrustProvider, FoundationalTrustProviderHistory.class);
			foundationalTrustProviderHistory.setEffectivetimes(updateFoundationalTrustProvider.getUpdatedDateTime());
			foundationalTrustProviderHistory.setIsActive(foundationalTrustProviderPutDto.isActive());
			foundationalTrustProviderHistory.setCreatedDateTime(updateFoundationalTrustProvider.getUpdatedDateTime());
			foundationalTrustProviderRepositoryHistory.create(foundationalTrustProviderHistory);
			foundationalTrustProviderResDto =  MetaDataUtils.setCreateMetaData(updateFoundationalTrustProvider, FoundationalTrustProviderResDto.class);
			response.setResponse(foundationalTrustProviderResDto);
			response.setResponsetime(LocalDateTime.now());
		}
		return response;
	}

}
