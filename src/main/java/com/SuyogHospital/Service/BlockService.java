package com.SuyogHospital.Service;

import java.util.List;

import com.SuyogHospital.Master.BlockMaster;

public interface BlockService {

	public Boolean addBlockService(BlockMaster blockMaster);
	public Boolean updateBlockService(BlockMaster blockMaster);
	public Boolean deleteBlockService(BlockMaster blockMaster);
	public BlockMaster getBlockService(Integer blockId);
	public List<BlockMaster> getAllBlockService();
}
