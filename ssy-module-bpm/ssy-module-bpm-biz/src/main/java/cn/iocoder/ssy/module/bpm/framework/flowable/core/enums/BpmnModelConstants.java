package cn.iocoder.ssy.module.bpm.framework.flowable.core.enums;

/**
 * BPMN XML 常量信息
 *
 * @author  Ssy
 */
public interface BpmnModelConstants {

    String BPMN_FILE_SUFFIX = ".bpmn";

    /**
     * BPMN 中的命名空间
     */
    String NAMESPACE = "http://flowable.org/bpmn";

    /**
     * BPMN UserTask 的扩展属性，用于标记候选人策略
     */
    String USER_TASK_CANDIDATE_STRATEGY = "candidateStrategy";
    /**
     * BPMN UserTask 的扩展属性，用于标记候选人参数
     */
    String USER_TASK_CANDIDATE_PARAM = "candidateParam";

}