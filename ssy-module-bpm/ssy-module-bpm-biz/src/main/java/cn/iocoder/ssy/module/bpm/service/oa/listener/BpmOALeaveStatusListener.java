package cn.iocoder.ssy.module.bpm.service.oa.listener;

import cn.iocoder.ssy.module.bpm.event.BpmProcessInstanceStatusEvent;
import cn.iocoder.ssy.module.bpm.event.BpmProcessInstanceStatusEventListener;
import cn.iocoder.ssy.module.bpm.service.oa.BpmOALeaveService;
import cn.iocoder.ssy.module.bpm.service.oa.BpmOALeaveServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * OA 请假单的结果的监听器实现类
 *
 * @author  Ssy
 */
@Component
public class BpmOALeaveStatusListener extends BpmProcessInstanceStatusEventListener {

    @Resource
    private BpmOALeaveService leaveService;

    @Override
    protected String getProcessDefinitionKey() {
        return BpmOALeaveServiceImpl.PROCESS_KEY;
    }

    @Override
    protected void onEvent(BpmProcessInstanceStatusEvent event) {
        leaveService.updateLeaveStatus(Long.parseLong(event.getBusinessKey()), event.getStatus());
    }

}
