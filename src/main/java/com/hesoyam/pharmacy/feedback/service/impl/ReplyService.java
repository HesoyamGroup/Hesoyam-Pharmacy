package com.hesoyam.pharmacy.feedback.service.impl;

import com.hesoyam.pharmacy.feedback.dto.ReplyDTO;
import com.hesoyam.pharmacy.feedback.events.OnComplaintRepliedEvent;
import com.hesoyam.pharmacy.feedback.exceptions.InvalidReplyRequest;
import com.hesoyam.pharmacy.feedback.model.Complaint;
import com.hesoyam.pharmacy.feedback.model.ComplaintStatus;
import com.hesoyam.pharmacy.feedback.model.Reply;
import com.hesoyam.pharmacy.feedback.repository.ReplyRepository;
import com.hesoyam.pharmacy.feedback.service.IComplaintService;
import com.hesoyam.pharmacy.feedback.service.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class ReplyService implements IReplyService {



    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private IComplaintService complaintService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    @Override
    @Transactional
    public Reply create(Reply reply) {
        return replyRepository.save(reply);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Reply reply(ReplyDTO replyDTO) throws InvalidReplyRequest {
        Reply reply = createReplyFromDTO(replyDTO);
        Complaint complaint = complaintService.findComplaintByIdAndComplaintStatus(replyDTO.getComplaintId(), ComplaintStatus.OPENED);
        if(complaint == null) throw new InvalidReplyRequest("Complaint could not be found or has been already answered.");

        try{
            reply = create(reply);
            complaint.setReply(reply);
            complaint = complaintService.save(complaint);
            applicationEventPublisher.publishEvent(new OnComplaintRepliedEvent(complaint));
        }catch (DataIntegrityViolationException dataIntegrityViolationException){
            throw new InvalidReplyRequest("Data integrity violation.");
        }

        return reply;
    }

    private Reply createReplyFromDTO(ReplyDTO replyDTO){
        return new Reply(replyDTO.getText(), replyDTO.getSysAdmin());
    }


}
